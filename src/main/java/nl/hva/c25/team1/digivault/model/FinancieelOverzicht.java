package nl.hva.c25.team1.digivault.model;

import java.util.List;

/**
 * @author Erwin, studentnummer 500889293
 * @since 9-12-2021
 */

public class FinancieelOverzicht {
    private int klantId;
    private String iban;
    private double saldo;
    private List<PortefeuilleItem> portefeuille;

    public FinancieelOverzicht(int klantId, String iban, double saldo, List<PortefeuilleItem> portefeuille) {
        super();
        this.klantId = klantId;
        this.iban = iban;
        this.saldo = saldo;
        this.portefeuille = portefeuille;
    }

    public FinancieelOverzicht(int klantId) {
        this(klantId, "", 0, null);
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public FinancieelOverzicht() {

    }

    public int getKlantId() {
        return klantId;
    }

    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }

    public List<PortefeuilleItem> getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(List<PortefeuilleItem> portefeuille) {
        this.portefeuille = portefeuille;
    }
}
