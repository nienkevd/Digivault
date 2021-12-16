package nl.hva.c25.team1.digivault.model;

import nl.hva.c25.team1.digivault.transfer.RegisterDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Anneke, studentnummer 500889251
 * @since 1-12-2021
 *
 * Review Anthon 8-12-2021
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

    public Naam(String voornaam, String tussenvoegsel, String achternaam) {
        this(0,voornaam,tussenvoegsel,achternaam);
    }

    public Naam(RegisterDto dto) {
        this(dto.getVoornaam(), dto.getTussenvoegsel(), dto.getAchternaam());
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
        return String.format("%s %s %s", voornaam, tussenvoegsel, achternaam);
    }
}
