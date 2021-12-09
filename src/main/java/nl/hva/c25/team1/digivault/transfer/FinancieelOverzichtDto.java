package nl.hva.c25.team1.digivault.transfer;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.PortefeuilleItem;
import nl.hva.c25.team1.digivault.model.Rekening;

import java.util.List;
/**
 * @author Nienke
 * @version 8-12-2021
 * Om het rekening en portefuille overzicht te tonen, via jason...
 */


public class FinancieelOverzichtDto {
    private int klantId;
    private String IBAN;
    private double saldo;
    private List<PortefeuilleItem> portefeuille;

    public FinancieelOverzichtDto(Klant klant, Rekening rekening, List<PortefeuilleItem> portefeuille) {
        this.klantId = klant.getKlantId();
        this.IBAN = rekening.getIBAN();
        this.saldo = rekening.getSaldo();
        this.portefeuille = portefeuille;
    }

    public FinancieelOverzichtDto(){
    }

    public int getKlantId() {
        return klantId;
    }

    public void setKlantId(int klantId) {
        this.klantId = klantId;
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

    public List<PortefeuilleItem> getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(List<PortefeuilleItem> portefeuille) {
        this.portefeuille = portefeuille;
    }
}
