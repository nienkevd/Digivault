package nl.hva.c25.team1.digivault.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 */

public class EuroKoers {
    private int euroKoersId;
    private LocalDate datum;
    private double koers;
    private int assetId;

    public EuroKoers(int euroKoersId, LocalDate datum, double koers, int assetId) {
        super();
        this.euroKoersId = euroKoersId;
        this.datum = datum;
        this.koers = koers;
        this.assetId = assetId;
    }

    public int getEuroKoersId() {
        return euroKoersId;
    }

    public void setEuroKoersId(int euroKoersId) {
        this.euroKoersId = euroKoersId;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public double getKoers() {
        return koers;
    }

    public void setKoers(double koers) {
        this.koers = koers;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    @Override
    public String toString() {
        return "EuroKoers{euroKoersId=" + euroKoersId + ", datum=" + datum + ", koers=" + koers + ", assetId="
                + assetId + '}';
    }
}
