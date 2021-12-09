package nl.hva.c25.team1.digivault.model;

/**
 * @author Erwin, studentnummer 500889293
 * @since 9-12-2021
 */

public class PortefeuilleItemOverzicht {
    private String afkorting;
    private String naam;
    private double dagkoers;
    private double hoeveelheid;

    public PortefeuilleItemOverzicht(String afkorting, String naam, double dagkoers, double hoeveelheid) {
        super();
        this.afkorting = afkorting;
        this.naam = naam;
        this.dagkoers = dagkoers;
        this.hoeveelheid = hoeveelheid;
    }

    public PortefeuilleItemOverzicht() {

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

    public double getDagkoers() {
        return dagkoers;
    }

    public void setDagkoers(double dagkoers) {
        this.dagkoers = dagkoers;
    }

    public double getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(double hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }

    @Override
    public String toString() {
        return "PortefeuilleItemOverzicht{" +
                "afkorting='" + afkorting + '\'' +
                ", naam='" + naam + '\'' +
                ", dagkoers=" + dagkoers +
                ", hoeveelheid=" + hoeveelheid +
                '}';
    }
}
