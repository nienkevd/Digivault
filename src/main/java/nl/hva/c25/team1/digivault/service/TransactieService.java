package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Deze klasse verzorgt de verwerking van een transactie op de service-laag. Het Java-object wordt vanuit de DB gevuld
 * met de juiste gegevens. Vervolgens worden, na een check of de transactie daadwerkelijk uitgevoerd kan worden, de
 * mutaties in de Java-objecten uitgevoerd. Vervolgens wordt de gevulde en gemuteerde transactie doorgezet naar de
 * repository-laag om de persistentie te regelen.
 *
 * @author Nienke
 * @author Anthon
 */
@Service
public class TransactieService {

    private KlantService klantService;
    private AssetService assetService;
    private BankService bankService;
    private RootRepository rootRepository;


    @Autowired
    public TransactieService(KlantService klantService, AssetService assetService, BankService bankService,
                             RootRepository rootRepository) {
        this.klantService = klantService;
        this.assetService = assetService;
        this.bankService = bankService;
        this.rootRepository = rootRepository;
    }

    /**
     * Deze methode:
     * 1. vult het transactie-object met gegevens uit de DB.
     * 2. checkt of het een geldige transactie betreft.
     * 3. voert de mutaties in het transactie-object uit.
     * 4. geeft dit gemuteerde object door aan de repo-laag voor de persistentie.
     *
     * @param transactie De transactie met alleen de gegevens uit de DTO (en actuele datum/tijd).
     * @return Geeft de transactie terug indien succesvol, anders null.
     */
    public Transactie voerTransactieUit(Transactie transactie) {
        vulTransactie(transactie);
        if (checkKoper(transactie) && checkVerkoper(transactie) && checkAccounts(transactie)) {
            doeTransactieMutaties(transactie);
            return rootRepository.voerTransactieUit(transactie);
        }
        return null;
    }

    // Deze methode voert de mutaties van deze transactie uit in de Java-objecten.
    void doeTransactieMutaties(Transactie transactie) {
        TransactiePartij koper = transactie.getKoper();
        TransactiePartij verkoper = transactie.getVerkoper();
        double mutatieBedrag;
        if (verkoper instanceof Bank) {
            mutatieBedrag = berekenWaardeTransactie(transactie) *
                    (1 + ((Bank) verkoper).getTransactiePercentage() / 100);
        } else {
            mutatieBedrag = berekenWaardeTransactie(transactie) * (1 - ((Bank) koper).getTransactiePercentage() / 100);
        }
        muteerRekening(verkoper.getRekening(), mutatieBedrag);
        muteerRekening(koper.getRekening(), 0 - mutatieBedrag);
        muteerPortefeuille(koper.getPortefeuille(), transactie.getAsset(), transactie.getAantalCryptos());
        muteerPortefeuille(verkoper.getPortefeuille(), transactie.getAsset(), 0 - transactie.getAantalCryptos());
    }

    // Deze methode muteert de portefeuilles
    void muteerPortefeuille(List<PortefeuilleItem> portefeuille, Asset asset, double aantal) {
        for (PortefeuilleItem portefeuilleItem : portefeuille) {
            if (portefeuilleItem.getAsset().getAfkorting().equals(asset.getAfkorting())) {
                portefeuilleItem.setHoeveelheid(portefeuilleItem.getHoeveelheid() + aantal);
            }
        }
    }

    // Deze methode muteert de rekeningen
    void muteerRekening(Rekening rekening, double bedrag) {
        rekening.setSaldo(rekening.getSaldo() + bedrag);
    }

    // Deze methode vult de kale transactie met de gegevens uit de DB.
    void vulTransactie(Transactie transactie) {
        if (transactie.getKoper() instanceof Bank) {
            transactie.setKoper(bankService.vindBankOpId(transactie.getKoper().getTransactiepartijId()));
        } else {
            transactie.setKoper(klantService.vindKlantOpKlantID(transactie.getKoper().getTransactiepartijId()));
        }
        if (transactie.getVerkoper() instanceof Bank) {
            transactie.setVerkoper(bankService.vindBankOpId(transactie.getVerkoper().getTransactiepartijId()));
        } else {
            transactie.setVerkoper(klantService.vindKlantOpKlantID(transactie.getVerkoper().getTransactiepartijId()));
        }
        transactie.setAsset(assetService.vindAssetOpId(transactie.getAsset().getAssetId()));
    }

    // Deze methode berekent de bruto transactie-waarde. D.w.z. zonder transactiekosten.
    public double berekenWaardeTransactie(Transactie transactie) {
        return transactie.getAsset().getDagKoers() * transactie.getAantalCryptos();
    }

    // Deze methode checkt of de koper voldoende geld heeft.
    public boolean checkKoper(Transactie transactie) {
        return transactie.getKoper().getRekening().getSaldo() >= berekenWaardeTransactie(transactie);
    }

    // Deze methode checkt of de verkoper voldoende crypto heeft.
    boolean checkVerkoper(Transactie transactie) {
        for (PortefeuilleItem portefeuilleItem : transactie.getVerkoper().getPortefeuille()) {
            if (portefeuilleItem.getAsset().getAfkorting().equals(transactie.getAsset().getAfkorting()))
                return portefeuilleItem.getHoeveelheid() >= transactie.getAantalCryptos();
        }
        return false;
    }

    // Deze methode checkt of de koper en verkoper accounts van elkaar verschillen.
    boolean checkAccounts (Transactie transactie) {
        return !(transactie.getVerkoper().getRekening() == transactie.getKoper().getRekening());
    }

}