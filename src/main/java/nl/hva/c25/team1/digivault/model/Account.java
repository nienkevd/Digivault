package nl.hva.c25.team1.digivault.model;

import com.fasterxml.jackson.annotation.JsonBackReference;


// review door Erwin, 1 december

/**
 * @author Sezi, studentnummer 500889525
 * @version 1-12-2021
 */


public class Account {
    private int accountId;
    private String emailadres;
    private String wachtwoord;

    @JsonBackReference
    private Klant klant;

    private Account (int accountId,String emailadres, String wachtwoord, Klant klant) {
        this.accountId = accountId;
        this.emailadres = emailadres;
        this.wachtwoord = wachtwoord;
        this.klant = klant;
    }

    public Account(String emailadres, String wachtwoord) {
        this.emailadres = emailadres;
        this.wachtwoord = wachtwoord;
        this.klant = null;
    }

    public Account () {
        this.emailadres = "onbekend";
        this.wachtwoord = "onbekend";
        this.klant = null;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    @Override
    public String toString() {
        return String.format("Account of %s met emailadres : %s en wachtwoord : %s",
                klant.getNaam().getAchternaam(), this.emailadres, this.wachtwoord);
    }
}
