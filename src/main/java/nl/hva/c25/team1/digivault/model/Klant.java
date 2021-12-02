package nl.hva.c25.team1.digivault.model;

import java.time.LocalDate;

public class Klant {
    private int klantId;
    private String bsn;
    private LocalDate geboortedatum;
    private Naam naam;
    private Adres adres;
    private Account account;
    private Rekening rekening;
    private Portefeuille portefeuille;

    private Klant(int klantId, String bsn, LocalDate geboortedatum, Naam naam, Adres adres,
                 Account account, Rekening rekening, Portefeuille portefeuille) {
        this.klantId = klantId;
        this.bsn = bsn;
        this.geboortedatum = geboortedatum;
        this.naam = naam;
        this.adres = adres;
        this.account = account;
        this.rekening = rekening;
        this.portefeuille = portefeuille;
    }


    public Klant(int klantId, String bsn, LocalDate geboortedatum) {
        this(klantId,bsn,geboortedatum, null, null,null, null, null);
    }


    public int getKlantId() {
        return klantId;
    }

    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Naam getNaam() {
        return naam;
    }

    public void setNaam(Naam naam) {
        this.naam = naam;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
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
                "klantId=" + klantId +
                ", bsn='" + bsn + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", naam=" + naam +
                ", adres=" + adres +
                ", account=" + account +
                ", rekening=" + rekening +
                ", portefeuille=" + portefeuille +
                '}';
    }
}
