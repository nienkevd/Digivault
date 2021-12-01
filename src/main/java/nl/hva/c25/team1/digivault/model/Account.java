package nl.hva.c25.team1.digivault.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Account {
    private String gebruikersnaam;
    private String wachtwoord;

    @JsonBackReference
    private Klant klant;

    public Account (String gebruikersnaam, String wachtwoord, Klant klant) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.klant = klant;
    }

    public Account(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.klant = null;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    @Override
    public String toString() {
        return String.format("Account of %s met gebruikersnaam : %s en wachtwoord : %s", klant.getAchternaam(), this.gebruikersnaam, this.wachtwoord);
    }
}
