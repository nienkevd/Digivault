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
        System.out.println(klant);
        Rekening rekening = new Rekening(0, genereerIban());
        klant.setRekening(rekening);
        System.out.println(klant);
        klant.setPortefeuille(aanmaakLegePortefeuille());
        System.out.println(klant);
        return rootRepository.slaKlantOp(klant);
    }

    public List<PortefeuilleItem> aanmaakLegePortefeuille() {
        System.out.println("spot1");
        List<PortefeuilleItem> legePortefeuille = new ArrayList<>();
        System.out.println("spot2");
        List<Asset> assetlist = new ArrayList<>();
        assetlist.add(new Asset(1,"A","Anthoncoin", 100));
        assetlist.add(new Asset(2,"AB","Annekecoin", 200));
        for (Asset asset: assetlist) {
            PortefeuilleItem portefeuilleItem = new PortefeuilleItem(asset.getAssetId(), 0);
            legePortefeuille.add(portefeuilleItem);
        }
        return legePortefeuille;
    }

    public String genereerIban() {
        return IbanService.IbanGenerator();
    }
}
