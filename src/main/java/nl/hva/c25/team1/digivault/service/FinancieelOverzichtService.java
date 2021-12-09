package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.RootRepository;

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

}
