package nl.hva.c25.team1.digivault.model;

import java.util.List;

/**
 * Author Nienke
 * Version 14-12-2021
 */

public abstract class TransactiePartij {
    private int transactiepartijId;
    private Rekening rekening;
    private List<PortefeuilleItem> portefeuille;

    protected TransactiePartij(int transactiepartijId, Rekening rekening, List<PortefeuilleItem> portefeuille) {
        this.transactiepartijId = transactiepartijId;
        this.rekening = rekening;
        this.portefeuille = portefeuille;
    }

    public int getTransactiepartijId() {
        return transactiepartijId;
    }

    public void setTransactiepartijId(int transactiepartijId) {
        this.transactiepartijId = transactiepartijId;
    }

    public Rekening getRekening() {
        return rekening;
    }

    public void setRekening(Rekening rekening) {
        this.rekening = rekening;
    }

    public List<PortefeuilleItem> getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(List<PortefeuilleItem> portefeuille) {
        this.portefeuille = portefeuille;
    }
}
