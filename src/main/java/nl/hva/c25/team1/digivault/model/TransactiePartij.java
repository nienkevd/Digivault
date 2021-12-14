package nl.hva.c25.team1.digivault.model;

import java.util.List;

/**
 * Author Nienke
 * Version 14-12-2021
 */

public abstract class TransactiePartij {
    private int transactiePartijId;
    private Rekening rekening;
    private List<PortefeuilleItem> portefeuille;



    public int getTransactiePartijId() {
        return transactiePartijId;
    }

    public void setTransactiePartijId(int transactiePartijId) {
        this.transactiePartijId = transactiePartijId;
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
