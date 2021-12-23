// Created by antho
// Creation date 23-12-2021

package nl.hva.c25.team1.digivault.transfer;

public class TransactieDTO {

    private int koperId, verkoperId;
    private String cryptoAfkorting;
    private double aantal;

    public TransactieDTO(int koperId, int verkoperId, String cryptoAfkorting, double aantal) {
        this.koperId = koperId;
        this.verkoperId = verkoperId;
        this.cryptoAfkorting = cryptoAfkorting;
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

    public String getCryptoAfkorting() {
        return cryptoAfkorting;
    }

    public void setCryptoAfkorting(String cryptoAfkorting) {
        this.cryptoAfkorting = cryptoAfkorting;
    }

    public double getAantal() {
        return aantal;
    }

    public void setAantal(double aantal) {
        this.aantal = aantal;
    }

}