package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service voor het registreren van een Klant
 *
 * @author Erwin, studentnummer 500889293
 * @since 8-12-2021
 */

@Service
public class RegistratieService {
    private RootRepository rootRepository;
    private AssetService assetService;

    public static int BEGINSALDO = 100000;      // Saldo dat een nieuwe klant meekrijgt op zijn rekening

    /**
     * Constructor van de RegistratieService
     * @param rootRepository RootRepository
     * @param assetService AssetService
     */
    @Autowired
    public RegistratieService(RootRepository rootRepository, AssetService assetService) {
        super();
        this.rootRepository = rootRepository;
        this.assetService = assetService;
    }

    /**
     * Methode voor het registreren van een klant, de klant krijgt een goed gevulde rekening en lege portefeuille mee:
     * een lijst van 20 assets die op 0 zijn gezet
     *
     * @author Anneke en Erwin
     *
     * @param klant Klant die geregistreerd moet worden
     * @return de geregistreerde Klant
     */
    public Klant registratie(Klant klant) {
        Rekening rekening = new Rekening(0, genereerIban());
        rekening.setSaldo(BEGINSALDO);
        klant.setRekening(rekening);
        klant.setPortefeuille(aanmaakLegePortefeuille());
        return rootRepository.slaKlantOp(klant);
    }

    /**
     * Methode die een lege portefeuille aanmaakt: een lijst van 20 assets die op 0 zijn gezet
     * @return de aangemaakte lege portefeuillelijst
     */
    public List<PortefeuilleItem> aanmaakLegePortefeuille() {
        List<PortefeuilleItem> legePortefeuille = new ArrayList<>();
        for (Asset asset: assetService.geefAlleAssets()) {
            PortefeuilleItem portefeuilleItem = new PortefeuilleItem(0);
            portefeuilleItem.setAsset(asset);
            portefeuilleItem.setKlant(new Klant(0));
            legePortefeuille.add(portefeuilleItem);
        }
        return legePortefeuille;
    }

    /**
     * Methode die doorverwijst naar de IbanService voor het aanmaken van een nieuwe DIVA-Iban
     * @return gegenereerde IBAN als String
     */
    public String genereerIban() {
        return IbanService.IbanGenerator();
    }
}
