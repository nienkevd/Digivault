package nl.hva.c25.team1.digivault.model;

/**
 * @author Erwin, studentnummer 500889293
 * @since 9-12-2021
 */

public class AssetMetAantal {
    private int assetId;
    private String afkorting;
    private String naam;
    private double dagkoers;
    private double aantal;

    public AssetMetAantal(int assetId, String afkorting, String naam, double dagkoers, double aantal) {
        super();
        this.assetId = assetId;
        this.afkorting = afkorting;
        this.naam = naam;
        this.dagkoers = dagkoers;
        this.aantal = aantal;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public AssetMetAantal() {

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

    public double getAantal() {
        return aantal;
    }

    public void setAantal(double aantal) {
        this.aantal = aantal;
    }

    @Override
    public String toString() {
        return "AssetMetAantal{" +
                "assetId=" + assetId +
                ", afkorting='" + afkorting + '\'' +
                ", naam='" + naam + '\'' +
                ", dagkoers=" + dagkoers +
                ", aantal=" + aantal +
                '}';
    }
}
