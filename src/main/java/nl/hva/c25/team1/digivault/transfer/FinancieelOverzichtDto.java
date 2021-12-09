package nl.hva.c25.team1.digivault.transfer;

import nl.hva.c25.team1.digivault.model.PortefeuilleItem;
import nl.hva.c25.team1.digivault.model.Rekening;

import java.util.List;
/**
 * @author Nienke
 * @version 8-12-2021
 * Om het rekening en portefuille overzicht te tonen, via jason...
 */


public class FinancieelOverzichtDto {
    private Rekening rekening;
    private List<PortefeuilleItem> portefeuille;

    public FinancieelOverzichtDto(Rekening rekening, List<PortefeuilleItem> portefeuille) {
        this.rekening = rekening;
        this.portefeuille = portefeuille;
    }

    public FinancieelOverzichtDto(){
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
