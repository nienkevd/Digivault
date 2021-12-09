package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import nl.hva.c25.team1.digivault.transfer.FinancieelOverzichtDto;

/**
 * @author Erwin, studentnummer 500889293
 * @version 9-12-2021
 */

public class FinancieelOverzichtService {

    private RootRepository rootRepository;

    public FinancieelOverzichtService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
    }

    public Klant vindFinancieelOverzicht(FinancieelOverzichtDto overzichtDto) {
        Klant klant = new Klant(overzichtDto.getKlantId(), overzichtDto.getIBAN(), overzichtDto.getSaldo(),
                overzichtDto.getPortefeuille());
        return klant;
    }
}
