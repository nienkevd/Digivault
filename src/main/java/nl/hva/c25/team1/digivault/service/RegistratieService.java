package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.repository.KlantDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
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
    public RegistratieService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
    }

    @PostMapping("/registratie")
    public Klant registratie(Klant klant) {
        Account account = new Account(klant.getAccount().getEmailadres(), klant.getAccount().getWachtwoord());
        Klant nieuweKlant = new Klant(0, klant.getBsn(), klant.getGeboortedatum());
        Naam naam = new Naam(0, klant.getNaam().getVoornaam(), klant.getNaam().getTussenvoegsel(),
                klant.getNaam().getAchternaam());
        Adres adres = new Adres(0, klant.getAdres().getStraat(), klant.getAdres().getHuisnummer(),
                klant.getAdres().getToevoeging(), klant.getAdres().getPostcode(), klant.getAdres().getWoonplaats());
        Rekening rekening = new Rekening(0, genereerIban());
        klant.setAccount(account);
        klant.setNaam(naam);
        klant.setAdres(adres);
        klant.setRekening(rekening);
        klant.setPortefeuille(aanmaakLegePortefeuille());
        return klant;
    }

    public List<PortefeuilleItem> aanmaakLegePortefeuille() {
        List<PortefeuilleItem> legePortefeuille = new ArrayList<>();
        for (Asset asset: assetService.geefAlleAssets()) {
            PortefeuilleItem portefeuilleItem = new PortefeuilleItem(asset.getAssetId(), 0);
            legePortefeuille.add(portefeuilleItem);
        }
        return legePortefeuille;
    }

    public String genereerIban() {
        return "NL20 DIVA 0001234567";
    }
}
