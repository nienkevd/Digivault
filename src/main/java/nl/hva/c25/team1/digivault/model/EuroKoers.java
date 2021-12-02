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

    /**
     * Constructor van klasse EuroKoers
     * @param euroKoersId de euroKoers
     * @param datum de betreffende datum
     * @param koers de bijbehorende koers
     * @param assetId de bijbehorende assetId
     */
    public EuroKoers(int euroKoersId, LocalDate datum, double koers, int assetId) {    // TODO private + kale constructor
        super();
        this.euroKoersId = euroKoersId;
        this.datum = datum;
        this.koers = koers;
        this.assetId = assetId;
    }

    /**
     * Lege constructor van de Klasse EuroKoers
     */
    public EuroKoers() {
        this(0,null, 0.0, 0);
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

    /**
     * ToString-methode EuroKoers
     * @return toString van klasse EuroKoers
     */
    @Override
    public String toString() {
        return "EuroKoers{euroKoersId=" + euroKoersId + ", datum=" + datum + ", koers=" + koers + ", assetId="
                + assetId + '}';
    }
}
