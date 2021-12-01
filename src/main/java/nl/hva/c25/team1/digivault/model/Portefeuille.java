// Created by antho
// Creation date 1-12-2021

package nl.hva.c25.team1.digivault.model;

import java.util.*;

public class Portefeuille {

    private int portefeuilleId;
    private double totaleWaarde;
    private Map<Asset, Double> assetLijst;

    private Portefeuille(int portefeuilleId, double totaleWaarde ,Map<Asset, Double> assetLijst) {
        super();
        this.portefeuilleId = portefeuilleId;
        this.totaleWaarde = totaleWaarde;
        this.assetLijst = assetLijst;
    }

    public Portefeuille(int portefeuilleId, double totaleWaarde) {
        this(portefeuilleId, totaleWaarde, new HashMap<Asset, Double>());
    }

    public Portefeuille() {
        this(0,0);
    }

    public int getPortefeuilleId() {
        return portefeuilleId;
    }

    public void setPortefeuilleId(int portefeuilleId) {
        this.portefeuilleId = portefeuilleId;
    }

    public double getTotaleWaarde() {
        return totaleWaarde;
    }

    public void setTotaleWaarde(double totaleWaarde) {
        this.totaleWaarde = totaleWaarde;
    }

    public Map<Asset, Double> getAssetLijst() {
        return assetLijst;
    }

    public void setAssetLijst(Map<Asset, Double> assetLijst) {
        this.assetLijst = assetLijst;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.format("Portefeuille:\n"));
        for (Asset asset: assetLijst.keySet()) stringBuilder.append(String.format("%-25s%+15.5f", asset,
                assetLijst.get(asset)));
        return stringBuilder.append(String.format("\n\nTotale waarde: %.2f EUR\n", totaleWaarde)).toString();
    }

}