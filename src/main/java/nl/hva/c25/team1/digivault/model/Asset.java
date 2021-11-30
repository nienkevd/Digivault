package nl.hva.c25.team1.digivault.model;

/**
 * @Author: Erwin, studentnummer 500889293
 * @Version: 30-11-2021
 */

public class Asset {
    private String afkorting;
    private String naam;
    private double euroKoers;

    public Asset(String afkorting, String naam, double euroKoers) {
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Asset{" + "afkorting='" + afkorting + '\'' + ", naam='" + naam + '\'' +
                ", euroKoers=" + euroKoers + '}';
    }
}
