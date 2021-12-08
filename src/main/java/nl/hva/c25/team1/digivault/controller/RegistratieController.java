package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Erwin, studentnummer 500889293
 * @version 8-12-2021
 */

public class RegistratieController {

    public RegistratieController() {
        super();
    }

    @PostMapping("/registratie")
    public Klant registratieHandler(
            @RequestParam(name="emailadres") String emailadres,
            @RequestParam(name="wachtwoord") String wachtwoord,
            @RequestParam(name="voornaam") String voornaam,
            @RequestParam(name="tussenvoegsel") String tussenvoegsel,
            @RequestParam(name="achternaam") String achternaam,
            @RequestParam(name="geboortedatum") LocalDate geboortedatum,
            @RequestParam(name="BSN") String BSN,
            @RequestParam(name="straat") String straat,
            @RequestParam(name="huisnummer") int huisnummer,
            @RequestParam(name="toevoeging") String toevoeging,
            @RequestParam(name="postcode") String postcode,
            @RequestParam(name="woonplaats") String woonplaats
            ) {
        Account account = new Account(emailadres, wachtwoord);
        Klant klant = new Klant(0, BSN, geboortedatum);
        Naam naam = new Naam(0, voornaam, tussenvoegsel, achternaam);
        Adres adres = new Adres(0, straat, huisnummer, toevoeging, postcode, woonplaats);
        Rekening rekening = new Rekening(0, "");
        PortefeuilleItem portefeuilleItem = new PortefeuilleItem(0,0);
        klant.setNaam(naam);
        klant.setAdres(adres);
        klant.setRekening(rekening);
        klant.setAccount(account);
        return klant;
    }

    public List<PortefeuilleItem> aanmaakLegePortefeuille() {
        List<PortefeuilleItem> legePortefeuille = new ArrayList<>();
        return legePortefeuille;
    }
}
