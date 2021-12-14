package nl.hva.c25.team1.digivault.model;

/**
 * Author Nienke
 * Version 14-12-2021
 */

public class Bank {
    private int bankId;
    private String bankNaam;
    private double transactiePercentage;

    public Bank(int bankId, String bankNaam, double transactiePercentage) {
        this.bankId = bankId;
        this.bankNaam = bankNaam;
        this.transactiePercentage = transactiePercentage;
    }

    public Bank(String bankNaam, double transactiePercentage) {
        this(0,bankNaam,transactiePercentage);
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankNaam() {
        return bankNaam;
    }

    public void setBankNaam(String bankNaam) {
        this.bankNaam = bankNaam;
    }

    public double getTransactiePercentage() {
        return transactiePercentage;
    }

    public void setTransactiePercentage(double transactiePercentage) {
        this.transactiePercentage = transactiePercentage;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankId=" + bankId +
                ", bankNaam='" + bankNaam + '\'' +
                ", transactiePercentage=" + transactiePercentage +
                '}';
    }
}
