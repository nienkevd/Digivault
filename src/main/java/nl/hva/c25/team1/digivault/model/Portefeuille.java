// Created by antho
// Creation date 1-12-2021

package nl.hva.c25.team1.digivault.model;

import java.util.*;

/**
 * @author Anthon van Dijk (studentnummer 500889247)
 */
public class Portefeuille {

    private double totaleWaarde;
    private Map<Asset, Double> assetLijst;
    private Klant klant;

    private Portefeuille(double totaleWaarde ,Map<Asset, Double> assetLijst, Klant klant) {
        super();
        this.totaleWaarde = totaleWaarde;
        this.assetLijst = assetLijst;
        this.klant = klant;
    }

    public Portefeuille(double totaleWaarde) {
        this(totaleWaarde, new HashMap<Asset, Double>(), null);
    }

    public Portefeuille() {
        this(0);
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

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.format("Portefeuille:\n"));
        for (Asset asset: assetLijst.keySet()) stringBuilder.append(String.format("%-25s%+15.5f", asset,
                assetLijst.get(asset)));
        return stringBuilder.append(String.format("\n\nTotale waarde: %.2f EUR\n", totaleWaarde)).toString();
    }

}