package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.repository.KlantDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import nl.hva.c25.team1.digivault.transfer.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Erwin, studentnummer 500889293
 */

@Service
public class RegistratieService {

    private RootRepository rootRepository;
    private AssetService assetService;

    @Autowired
    public RegistratieService(RootRepository rootRepository, AssetService assetService) {
        super();
        this.rootRepository = rootRepository;
        this.assetService = assetService;
    }

    public Klant registratie(Klant klant) {
        Rekening rekening = new Rekening(0, genereerIban());
        rekening.setSaldo(100000);
        klant.setRekening(rekening);
        klant.setPortefeuille(aanmaakLegePortefeuille());
        return rootRepository.slaKlantOp(klant);
    }

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

    public String genereerIban() {
        return IbanService.IbanGenerator();
    }
}
