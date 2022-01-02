package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.repository.*;
import nl.hva.c25.team1.digivault.transfer.TransactieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nienke
 * @author Anthon
 */
@Service
public class TransactieService {

    private TransactieDAO transactieDAO;
    private KlantService klantService;
    private AssetService assetService;
    private BankService bankService;
    private RootRepository rootRepository;


    @Autowired
    public TransactieService(TransactieDAO transactieDAO, KlantService klantService, AssetService assetService,
                             BankService bankService, RootRepository rootRepository) {
        this.transactieDAO = transactieDAO;
        this.klantService = klantService;
        this.assetService = assetService;
        this.bankService = bankService;
        this.rootRepository = rootRepository;
    }

    public Transactie voerTransactieUit(Transactie transactie) {
        vulTransactie(transactie);
        boolean transactieOk = checkKoper(transactie) && checkVerkoper(transactie) && checkAccounts(transactie);
//        System.out.println(transactieOk);
        if (true) { // TODO: kijken naar voorwaarden!!!
            TransactiePartij koper = transactie.getKoper();
            TransactiePartij verkoper = transactie.getVerkoper();
            Rekening koperRekening = koper.getRekening();
            Rekening verkoperRekening = verkoper.getRekening();
            List<PortefeuilleItem> koperPortefeuille = koper.getPortefeuille();
            List<PortefeuilleItem> verkoperPortefeuille = verkoper.getPortefeuille();
            double saldoKoperRekening = koperRekening.getSaldo();
            double saldoVerkoperRekening = verkoperRekening.getSaldo();
            boolean verkoperIsBank = (verkoper instanceof Bank);
            double mutatieBedrag;
            if (verkoperIsBank) {
                mutatieBedrag = berekenWaardeTransactie(transactie) *
                        (1 + ((Bank) verkoper).getTransactiePercentage() / 100);
            } else {
                mutatieBedrag = berekenWaardeTransactie(transactie) *
                        (1 - ((Bank) koper).getTransactiePercentage() / 100);
            }
            // verhoog rekening verkoper
            verkoperRekening.setSaldo(saldoVerkoperRekening + mutatieBedrag);
            // verlaag rekening koper
            koperRekening.setSaldo(saldoKoperRekening - mutatieBedrag);
            // verhoog portefeuilleItem koper
            for (PortefeuilleItem portefeuilleItem : koperPortefeuille) {
                if (portefeuilleItem.getAsset().getAfkorting().equals(transactie.getAsset().getAfkorting())) {
                    portefeuilleItem.setHoeveelheid(portefeuilleItem.getHoeveelheid() + transactie.getAantalCryptos());
                }
            }
            // verlaag portefeuilleItem verkoper
            for (PortefeuilleItem portefeuilleItem : verkoperPortefeuille) {
                if (portefeuilleItem.getAsset().getAfkorting().equals(transactie.getAsset().getAfkorting())) {
                    portefeuilleItem.setHoeveelheid(portefeuilleItem.getHoeveelheid() - transactie.getAantalCryptos());
                }
            }
            return rootRepository.voerTransactieUit(transactie);
        }
        return null;
    }

    private Transactie vulTransactie(Transactie transactie) {
        TransactiePartij koper = transactie.getKoper();
        TransactiePartij verkoper = transactie.getVerkoper();
        int koperId = koper.getTransactiepartijId();
        int verkoperId = verkoper.getTransactiepartijId();
        if (koper instanceof Bank) {
            transactie.setKoper(bankService.vindBankOpId(koperId));
        } else {
            transactie.setKoper(klantService.vindKlantOpKlantID(koperId));
        }
        if (verkoper instanceof Bank) {
            transactie.setVerkoper(bankService.vindBankOpId(verkoperId));
        } else {
            transactie.setVerkoper(klantService.vindKlantOpKlantID(verkoperId));
        }
        transactie.setAsset(assetService.vindAssetOpId(transactie.getAsset().getAssetId()));
        return transactie;
    }

    public double berekenWaardeTransactie(Transactie transactie) {
        return transactie.getAsset().getDagKoers() * transactie.getAantalCryptos();
    }

    public boolean checkKoper(Transactie transactie) {
        return transactie.getKoper().getRekening().getSaldo() >= berekenWaardeTransactie(transactie);
    }

    public boolean checkVerkoper(Transactie transactie) {
        for (PortefeuilleItem portefeuilleItem : transactie.getVerkoper().getPortefeuille()) {
            if (portefeuilleItem.getAsset() == transactie.getAsset()) {
                return portefeuilleItem.getHoeveelheid() >= transactie.getAantalCryptos();
            }
        }
        return false;
    }

    public boolean checkAccounts (Transactie transactie) {
        return !(transactie.getVerkoper().getRekening() == transactie.getKoper().getRekening());
    }

    public Transactie vindTransactieOpTransactieId(int transactieId) {
        return transactieDAO.vindTransactieOpTransactieId(transactieId);
    }

    public List<Transactie> vindAlleTransactiesOpVerkoper(TransactiePartij verkoper) {
        return transactieDAO.vindAlleTransactiesOpVerkoper(verkoper);
    }

    public List<Transactie> vindAlleTransactiesOpKoper(TransactiePartij koper) {
        return transactieDAO.vindAlleTransactiesOpVerkoper(koper);
    }

}


