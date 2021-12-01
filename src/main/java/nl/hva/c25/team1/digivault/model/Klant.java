package nl.hva.c25.team1.digivault.model;

import java.time.LocalDate;

public class Klant {
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
    private String emailadres;
    private Account account;
    private Rekening rekening;
    private Portefeuille portefeuille;

    private Klant(String voornaam, String tussenvoegsel,
                  String achternaam, LocalDate geboortedatum,
                  String bsn, String straat, int huisnummer,
                  String toevoeging, String postcode, String woonplaats,
                  String emailadres, Account account, Rekening rekening, Portefeuille portefeuille) {
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.bsn = bsn;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.toevoeging = toevoeging;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.emailadres = emailadres;
        this.account = account;
        this.rekening = rekening;
        this.portefeuille = portefeuille;

    }

    public Klant(String voornaam, String tussenvoegsel, String achternaam,
                 LocalDate geboortedatum, String bsn, String straat,
                 int huisnummer, String toevoeging, String postcode,
                 String woonplaats, String emailadres) {
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.bsn = bsn;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.toevoeging = toevoeging;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.emailadres = emailadres;
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

    public String getEmailadres() {
        return emailadres;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Rekening getRekening() {
        return rekening;
    }

    public void setRekening(Rekening rekening) {
        this.rekening = rekening;
    }

    public Portefeuille getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(Portefeuille portefeuille) {
        this.portefeuille = portefeuille;
    }

    @Override
    public String toString() {
        return "Klant{" +
                "voornaam='" + voornaam + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", bsn='" + bsn + '\'' +
                ", straat='" + straat + '\'' +
                ", huisnummer=" + huisnummer +
                ", toevoeging='" + toevoeging + '\'' +
                ", postcode='" + postcode + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                ", emailadres='" + emailadres + '\'' +
                '}';
    }
}
