package nl.hva.c25.team1.digivault.model;

// review door Erwin, 1 december

public class Rekening {
    private String IBAN;
    private double saldo;

    public Rekening(String IBAN, double saldo) {
        this.IBAN = IBAN;
        this.saldo = saldo;
    }

    public Rekening() {
        this.IBAN = "onbekend";
        this.saldo = 0;
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
