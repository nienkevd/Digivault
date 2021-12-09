package nl.hva.c25.team1.digivault.transfer;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Adres;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Naam;

import java.time.LocalDate;

/**
 * @author Nienke
 * @version 8-12-2021
 */

public class RegisterDto {
    private String emailadres;
    private String wachtwoord;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private LocalDate geboortedatum;
    private String bsn;
    private String straat;
    private int huisnummer;
    private String toevoeging;
    private String postcode;
    private String woonplaats;

    public RegisterDto(Klant klant, Naam naam, Adres adres, Account account){
        this.emailadres = account.getEmailadres();
        this.wachtwoord = account.getWachtwoord();
        this.voornaam = naam.getVoornaam();
        this.tussenvoegsel = naam.getTussenvoegsel();
        this.achternaam = naam.getAchternaam();
        this.geboortedatum = klant.getGeboortedatum();
        this.bsn = klant.getBsn();
        this.straat = adres.getStraat();
        this.huisnummer = adres.getHuisnummer();
        this.toevoeging = adres.getToevoeging();
        this.postcode = adres.getPostcode();
        this.woonplaats = adres.getWoonplaats();
    }

    public RegisterDto(){

    }

    public String getEmailadres() {
        return emailadres;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
}
