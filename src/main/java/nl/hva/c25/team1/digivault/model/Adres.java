package nl.hva.c25.team1.digivault.model;

import nl.hva.c25.team1.digivault.transfer.RegisterDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Anneke, studentnummer 500889251
 * @since 1-12-2021
 *
 */

public class Adres {
    private int adresId;
    @Pattern(regexp = "[a-zA-Z_]+", message = "Vul een geldige straatnaam in")
    @NotNull(message = "Vul een straat in")
    private String straat;
    @Pattern(regexp = "\\d+", message = "Vul een geldig huisnummer in")
    @NotNull(message = "Vul een huisnummer in")
    private int huisnummer;
    private String toevoeging;
    @Pattern(regexp = "/^\\d{4}[a-z]{2}$/i", message = "Vul een geldige postcode in")
    @NotNull(message = "Vul een postcode in")
    private String postcode;
    @Pattern(regexp = "[a-zA-Z_]+", message = "Vul een geldige woonplaats in")
    @NotNull(message = "Vul een woonplaats in")
    private String woonplaats;

    public Adres(int adresId, String straat, int huisnummer, String toevoeging,
                 String postcode, String woonplaats) {
        this.adresId = adresId;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.toevoeging = toevoeging;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
    }

    public Adres(String straat, int huisnummer, String toevoeging, String postcode, String woonplaats) {
        this(0,straat,huisnummer,toevoeging,postcode,woonplaats);
    }

    public Adres(RegisterDto dto) {
        this(dto.getStraat(), dto.getHuisnummer(), dto.getToevoeging(), dto.getPostcode(), dto.getWoonplaats());
    }

    public int getAdresId() {
        return adresId;
    }

    public void setAdresId(int adresId) {
        this.adresId = adresId;
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

    @Override
    public String toString() {
        return "Adres{" +
                "adresId=" + adresId +
                ", straat='" + straat + '\'' +
                ", huisnummer=" + huisnummer +
                ", toevoeging='" + toevoeging + '\'' +
                ", postcode='" + postcode + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                '}';
    }
}
