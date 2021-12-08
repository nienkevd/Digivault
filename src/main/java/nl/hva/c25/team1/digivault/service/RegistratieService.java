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
    public RegistratieService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
    }
//      andere manier? :
//    public Klant registratie(RegisterDto registerDto) {
//        Account account = new Account(0, registerDto.getEmailadres(), registerDto.getWachtwoord());
//        Naam naam = new Naam(0, registerDto.getVoornaam(), registerDto.getTussenvoegsel(),
//                registerDto.getAchternaam());
//        Adres adres = new Adres(0, registerDto.getStraat(), registerDto.getHuisnummer(),
//                registerDto.getToevoeging(), registerDto.getPostcode(), registerDto.getWoonplaats());
//        Rekening rekening = new Rekening(0, genereerIban());
//        Klant klant = new Klant(registerDto.getBsn(), registerDto.getGeboortedatum(), naam, adres, account,
//                rekening, null);
//        // aanmaakLegePortefeuille geeft hij foutmelding op
//        return rootRepository.slaKlantOp(klant);
//    }

    public Klant registratie(Klant klant) {
        Account account = new Account(0, klant.getAccount().getEmailadres(), klant.getAccount().getWachtwoord());
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
        return rootRepository.slaKlantOp(klant);
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
