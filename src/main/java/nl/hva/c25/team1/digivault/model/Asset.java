package nl.hva.c25.team1.digivault.model;

import java.util.Objects;

// review door Sezi, 1 december

/**
 * @author Erwin, studentnummer 500889293
 * @version 1-12-2021
 */

public class Asset {
    private String afkorting;
    private String naam;
    private double euroKoers;

    /**
     *
     * @param afkorting
     * @param naam
     * @param euroKoers
     */
    public Asset(String afkorting, String naam, double euroKoers) {
        super();
        this.afkorting = afkorting;
        this.naam = naam;
        this.euroKoers = euroKoers;
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

    public double getEuroKoers() {
        return euroKoers;
    }

    public void setEuroKoers(double euroKoers) {
        this.euroKoers = euroKoers;
    }

    /**
     *
     * @param o
     * @return equals-waarde
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset = (Asset) o;
        return Double.compare(asset.euroKoers, euroKoers) == 0 && Objects.equals(afkorting, asset.afkorting)
                && Objects.equals(naam, asset.naam);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(afkorting, naam, euroKoers);
    }

    /**
     *
     * @return toString
     */
    @Override
    public String toString() {
        return "Asset{afkorting='" + afkorting + '\'' + ", naam='" + naam + '\'' + ", euroKoers=" + euroKoers + '}';
    }
}
