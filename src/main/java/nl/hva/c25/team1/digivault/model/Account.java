package nl.hva.c25.team1.digivault.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import nl.hva.c25.team1.digivault.transfer.RegisterDto;

// review door Erwin, 6 december

/**
 * @author Sezi, studentnummer 500889525
 * @version 6-12-2021
 */

public class Account {
    private int accountId;
    private String emailadres;
    private String wachtwoord;

    @JsonBackReference
    private Klant klant;

    private Account (int accountId,String emailadres, String wachtwoord, Klant klant) {
        super();
        this.accountId = accountId;
        this.emailadres = emailadres;
        this.wachtwoord = wachtwoord;
        this.klant = klant;
    }


    public Account(String emailadres, String wachtwoord) { this(0, emailadres, wachtwoord, null);}

    public Account () { this(0, "", "", null);}

    public Account(int accountId, String emailadres, String wachtwoord) {
        this(accountId, emailadres,wachtwoord, null);
    }

    public Account(String emailadres, String wachtwoord) {
        this(0, emailadres, wachtwoord);
    }

    public Account(int accountId) {
        this(accountId, "onbekend", "onbekend");
    }

    public Account () {
        this(0);
    }

    public Account(RegisterDto dto){
        this(dto.getEmailadres(),dto.getWachtwoord());
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
        return String.format("Account of %s %s %s met emailadres : %s en wachtwoord : %s",
                klant.getNaam().getVoornaam(), klant.getNaam().getTussenvoegsel(), klant.getNaam().getAchternaam(), this.emailadres, this.wachtwoord);
    }
}
