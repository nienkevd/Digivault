package nl.hva.c25.team1.digivault.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Author Nienke
 * Version 14-12-2021
 */

public class Transactie {
    private int transactieId;
    private TransactiePartij koper;
    private TransactiePartij verkoper;
    private LocalDate transactieDatum;
    private LocalTime transactieTijd;
    private Asset asset;
    private double aantalCryptos;

    public Transactie(int transactieId, TransactiePartij koper, TransactiePartij verkoper,
                      LocalDate transactieDatum, LocalTime transactieTijd, Asset asset, double aantalCryptos) {
        this.transactieId = transactieId;
        this.koper = koper;
        this.verkoper = verkoper;
        this.transactieDatum = transactieDatum;
        this.transactieTijd = transactieTijd;
        this.asset = asset;
        this.aantalCryptos = aantalCryptos;
    }

    public Transactie(TransactiePartij koper, TransactiePartij verkoper, LocalDate transactieDatum, LocalTime transactieTijd,
                      Asset asset, double aantalCryptos) {
        this(0,koper,verkoper,transactieDatum, transactieTijd,asset,aantalCryptos);
    }

    public Transactie(int transactieId, LocalDate transactieDatum, LocalTime transactieTijd, double aantalCryptos){
        this(transactieId, null, null, transactieDatum, transactieTijd, null, aantalCryptos);
    }



    public int getTransactieId() {
        return transactieId;
    }

    public void setTransactieId(int transactieId) {
        this.transactieId = transactieId;
    }

    public TransactiePartij getKoper() {
        return koper;
    }

    public void setKoper(TransactiePartij koper) {
        this.koper = koper;
    }

    public TransactiePartij getVerkoper() {
        return verkoper;
    }

    public void setVerkoper(TransactiePartij verkoper) {
        this.verkoper = verkoper;
    }

    public LocalDate getTransactieDatum() {
        return transactieDatum;
    }

    public void setTransactieDatum(LocalDate transactieDatum) {
        this.transactieDatum = transactieDatum;
    }

    public LocalTime getTransactieTijd() { return transactieTijd;}

    public void setTransactieTijd(LocalTime transactieTijd) { this.transactieTijd = transactieTijd;}

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public double getAantalCryptos() {
        return aantalCryptos;
    }

    public void setAantalCryptos(double aantalCryptos) {
        this.aantalCryptos = aantalCryptos;
    }

    @Override
    public String toString() {
        return "Transactie{" +
                "transactieId=" + transactieId +
                ", koper=" + koper +
                ", verkoper=" + verkoper +
                ", transactieDatum=" + transactieDatum +
                ", transactieTijd=" + transactieTijd +
                ", asset=" + asset +
                ", aantalCryptos=" + aantalCryptos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transactie that = (Transactie) o;
        return transactieId == that.transactieId && Double.compare(that.aantalCryptos, aantalCryptos) == 0 && Objects.equals(koper, that.koper) && Objects.equals(verkoper, that.verkoper) && Objects.equals(transactieDatum, that.transactieDatum) && Objects.equals(transactieTijd, that.transactieTijd) && Objects.equals(asset, that.asset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactieId, koper, verkoper, transactieDatum, transactieTijd, asset, aantalCryptos);
    }
}
