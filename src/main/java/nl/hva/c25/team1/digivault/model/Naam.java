package nl.hva.c25.team1.digivault.model;

/**
 *
 * @author Anneke, studentnummer 500889251
 * @version 1-12-2021
 */

public class Naam {
    private int naamId;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;

    public Naam(int naamId, String voornaam, String tussenvoegsel, String achternaam) {
        this.naamId = naamId;
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
    }

    public int getNaamId() {
        return naamId;
    }

    public void setNaamId(int naamId) {
        this.naamId = naamId;
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

    @Override
    public String toString() {
        return "Naam{" +
                "naamId=" + naamId +
                ", voornaam='" + voornaam + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                '}';
    }
}
