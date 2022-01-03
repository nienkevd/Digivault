package nl.hva.c25.team1.digivault.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Nienke
 * @author Anthon
 */

public class Transactie {

    private int transactieId;
    private TransactiePartij koper, verkoper;
    private LocalDate transactieDatum;
    private LocalTime transactieTijd;
    private Asset asset;
    private double aantalCryptos;

    private Transactie(int transactieId, TransactiePartij koper, TransactiePartij verkoper, LocalDate transactieDatum,
                       LocalTime transactieTijd, Asset asset, double aantalCryptos) {
        this.transactieId = transactieId;
        this.koper = koper;
        this.verkoper = verkoper;
        this.transactieDatum = transactieDatum;
        this.transactieTijd = transactieTijd;
        this.asset = asset;
        setAantalCryptos(aantalCryptos);
    }

    public Transactie(LocalDate transactieDatum, LocalTime transactieTijd, double aantalCryptos){
        this(0, null, null, transactieDatum, transactieTijd, null, aantalCryptos);
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

    public LocalTime getTransactieTijd() {
        return transactieTijd;
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

    public void setAantalCryptos(double aantalCryptos) throws IllegalArgumentException {
        if (aantalCryptos > 0) {
            this.aantalCryptos = aantalCryptos;
        } else {
            throw new IllegalArgumentException("Aantal cryptomunten moet een positief getal zijn!");
        }
    }

    @Override
    public String toString() {
        return String.format("%5d%5d%5d%15s%15s%10s%15.5f", transactieId, koper.getTransactiepartijId(),
                verkoper.getTransactiepartijId(), transactieDatum, transactieTijd, asset.getAfkorting(), aantalCryptos);
    }

}