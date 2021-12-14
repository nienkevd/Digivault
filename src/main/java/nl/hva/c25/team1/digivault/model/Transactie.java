package nl.hva.c25.team1.digivault.model;

import java.time.LocalDate;

/**
 * Author Nienke
 * Version 14-12-2021
 */

public class Transactie extends TransactiePartij {
    private int transactieId;
    private TransactiePartij koper;
    private TransactiePartij verkoper;
    private LocalDate tijdstip;
    private Asset asset;
    private double aantalCryptos;

    public Transactie(int transactieId, TransactiePartij koper, TransactiePartij verkoper,
                      LocalDate tijdstip, Asset asset, double aantalCryptos) {
        this.transactieId = transactieId;
        this.koper = koper;
        this.verkoper = verkoper;
        this.tijdstip = tijdstip;
        this.asset = asset;
        this.aantalCryptos = aantalCryptos;
    }

    public Transactie(TransactiePartij koper, TransactiePartij verkoper, LocalDate tijdstip,
                      Asset asset, double aantalCryptos) {
        this(0,koper,verkoper,tijdstip,asset,aantalCryptos);
    }

    public Transactie(int transactieId, LocalDate tijdstip, double aantalCryptos){
        this(transactieId, null, null, tijdstip, null, aantalCryptos);
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

    public LocalDate getTijdstip() {
        return tijdstip;
    }

    public void setTijdstip(LocalDate tijdstip) {
        this.tijdstip = tijdstip;
    }

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
                ", tijdstip=" + tijdstip +
                ", asset=" + asset +
                ", aantalCryptos=" + aantalCryptos +
                '}';
    }
}
