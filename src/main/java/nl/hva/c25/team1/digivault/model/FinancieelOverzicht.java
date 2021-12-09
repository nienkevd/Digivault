package nl.hva.c25.team1.digivault.model;

import java.util.List;

/**
 * @author Erwin, studentnummer 500889293
 * @since 9-12-2021
 */

public class FinancieelOverzicht {
    private int klantId;
    private Rekening rekening;
    private List<PortefeuilleItem> portefeuille;

    public FinancieelOverzicht(int klantId, Rekening rekening, List<PortefeuilleItem> portefeuille) {
        super();
        this.klantId = klantId;
        this.rekening = rekening;
        this.portefeuille = portefeuille;
    }

    public int getKlantId() {
        return klantId;
    }

    public void setKlantId(int klantId) {
        this.klantId = klantId;
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
