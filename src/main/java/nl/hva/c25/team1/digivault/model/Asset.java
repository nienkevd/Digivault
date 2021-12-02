package nl.hva.c25.team1.digivault.model;

import java.util.Objects;

// review door Sezi, 1 december

/**
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 */

public class Asset {
    private int assetId;
    private String afkorting;
    private String naam;
    private double dagKoers;

    /**
     * Constructor van de klasse Asset
     * @param assetId de id van een asset
     * @param afkorting de afkorting van een asset
     * @param naam de naam van een asset
     * @param dagKoers de huidige waarde van een asset
     */
    public Asset(int assetId, String afkorting, String naam, double dagKoers) {     // TODO private + kale constructor
        super();
        this.assetId = assetId;
        this.afkorting = afkorting;
        this.naam = naam;
        this.dagKoers = dagKoers;
    }

    /**
     * Lege constructor van de Klasse Asset
     */
    public Asset() {
        this(0,"", "", 0.0);
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getAfkorting() {
        return afkorting;
    }

    public void setAfkorting(String afkorting) {
        this.afkorting = afkorting;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getDagKoers() {
        return dagKoers;
    }

    public void setDagKoers(double dagKoers) {
        this.dagKoers = dagKoers;
    }

    /**
     * Equals-methode Asset
     * @param other te vergelijken Asset
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Asset asset = (Asset) other;
        return assetId == asset.assetId;
    }

    /**
     * HashCode Asset
     * @return Hash assetId
     */
    @Override
    public int hashCode() {
        return assetId;
    }

    /**
     * ToString-methode Asset
     * @return toString van klasse Asset
     */
    @Override
    public String toString() {
        return "Asset{assetId=" + assetId + ", afkorting='" + afkorting + '\'' + ", naam='" + naam + '\'' +
                ", dagKoers=" + dagKoers + '}';
    }
}
