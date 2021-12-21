package nl.hva.c25.team1.digivault.model;

import nl.hva.c25.team1.digivault.transfer.RegisterDto;

/**
 *
 * @author Anneke, studentnummer 500889251
 * @since 1-12-2021
 *
 * Review Anthon 8-12-2021
 */

public class Naam {
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;

    public Naam(String voornaam, String tussenvoegsel, String achternaam) {
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
    }

    public Naam(RegisterDto dto) {
        this(dto.getVoornaam(), dto.getTussenvoegsel(), dto.getAchternaam());
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
        return String.format("%s %s %s", voornaam, tussenvoegsel, achternaam);
    }
}
