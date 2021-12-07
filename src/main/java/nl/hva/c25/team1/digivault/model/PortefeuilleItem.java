// Created by antho
// Creation date 6-12-2021

package nl.hva.c25.team1.digivault.model;

/**
 * @author Anthon van Dijk (studentnummer 500889247)
 */
public class PortefeuilleItem {

    private int portefeuilleItemId;
    private double hoeveelheid;
    private Klant klant;
    private Asset asset;

    private PortefeuilleItem(int portefeuilleItemId, double hoeveelheid, Klant klant, Asset asset) {
        this.portefeuilleItemId = portefeuilleItemId;
        this.hoeveelheid = hoeveelheid;
        this.klant = klant;
        this.asset = asset;
    }

    public PortefeuilleItem(int portefeuilleItemId, double hoeveelheid) {
        this(portefeuilleItemId, hoeveelheid, null, null);
    }

    public int getPortefeuilleItemId() {
        return portefeuilleItemId;
    }

    public void setPortefeuilleItemId(int portefeuilleItemId) {
        this.portefeuilleItemId = portefeuilleItemId;
    }

    public double getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(double hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    @Override
    public String toString() {
        return String.format("%-25s%+15.5f", asset, hoeveelheid);
    }

}