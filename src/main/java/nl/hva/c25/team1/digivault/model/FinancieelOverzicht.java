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
    private List<AssetMetAantal> assetMetAantal;

    public FinancieelOverzicht(int klantId, String iban, double saldo, List<AssetMetAantal> assetMetAantal) {
        super();
        this.klantId = klantId;
        this.iban = iban;
        this.saldo = saldo;
        this.assetMetAantal = assetMetAantal;
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

    public List<AssetMetAantal> getAssetMetAantal() {
        return assetMetAantal;
    }

    public void setAssetMetAantal(List<AssetMetAantal> assetMetAantal) {
        this.assetMetAantal = assetMetAantal;
    }

    @Override
    public String toString() {
        return "FinancieelOverzicht{" +
                "klantId=" + klantId +
                ", iban='" + iban + '\'' +
                ", saldo=" + saldo +
                ", assetMetAantal=" + assetMetAantal +
                '}';
    }
}
