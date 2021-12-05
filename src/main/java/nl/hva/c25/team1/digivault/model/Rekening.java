package nl.hva.c25.team1.digivault.model;

// review door Erwin, 1 december

/**
 * @author Sezi, studentnummer 500889525
 * @version 4-12-2021
 */

public class Rekening {
    private int rekeningId;
    private String IBAN;
    private double saldo;

    private Rekening(int rekeningId, String IBAN, double saldo) {
        super();
        this.rekeningId = rekeningId;
        this.IBAN = IBAN;
        this.saldo = saldo;
    }

    public Rekening (int rekeningId, String IBAN) {
        this(rekeningId, IBAN, 0);
    }

    public Rekening() {
        this(0, "onbekend", 0);
    }

    public int getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(int rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String toString() {
        return String.format("Rekeningnummer %s met saldo %.2f", this.IBAN, this.saldo);
    }
}
