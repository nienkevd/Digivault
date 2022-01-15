package nl.hva.c25.team1.digivault.model;

import nl.hva.c25.team1.digivault.transfer.RegisterDto;

/**
 *
 * @author Anneke, studentnummer 500889251
 * @since 1-12-2021
 *
 */

public class Adres {
    private int adresId;
    private String postcode;
    private int huisnummer;
    private String toevoeging;

    /**
     *
     * All args constructor Adres
     * @param adresId adres van klant
     * @param postcode postcode van klant
     * @param huisnummer huisnummer van klant
     * @param toevoeging toevoeging huisnummer
     */
    public Adres(int adresId, String postcode, int huisnummer, String toevoeging) {
        this.adresId = adresId;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.toevoeging = toevoeging;
    }

    /**
     *
     * Constructor Adres zonder adresId
     * @param postcode van klant
     * @param huisnummer van klant
     * @param toevoeging van huisnummer
     */
    public Adres(String postcode, int huisnummer, String toevoeging) {
        this(0, postcode, huisnummer, toevoeging);
    }

    /**
     *
     * Constructor Adres voor in RegisterDto
     * @param dto voor registratie van klant + adres
     */
    public Adres(RegisterDto dto) {
        this(dto.getPostcode(), dto.getHuisnummer(), dto.getToevoeging());
    }

    public int getAdresId() {
        return adresId;
    }

    public void setAdresId(int adresId) {
        this.adresId = adresId;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
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

    @Override
    public String toString() {
        return "Adres{" +
                "adresId=" + adresId +
                ", postcode='" + postcode + '\'' +
                ", huisnummer=" + huisnummer +
                ", toevoeging='" + toevoeging + '\'' +
                '}';
    }
}
