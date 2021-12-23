// Created by antho
// Creation date 23-12-2021

package nl.hva.c25.team1.digivault.transfer;

public class TransactieDTO {

    private int koperId, verkoperId, assetId;
    private double aantal;

    public TransactieDTO(int koperId, int verkoperId, int assetId, double aantal) {
        this.koperId = koperId;
        this.verkoperId = verkoperId;
        this.assetId = assetId;
        this.aantal = aantal;
    }

    public int getKoperId() {
        return koperId;
    }

    public void setKoperId(int koperId) {
        this.koperId = koperId;
    }

    public int getVerkoperId() {
        return verkoperId;
    }

    public void setVerkoperId(int verkoperId) {
        this.verkoperId = verkoperId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public double getAantal() {
        return aantal;
    }

    public void setAantal(double aantal) {
        this.aantal = aantal;
    }

}