package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.stereotype.Service;

/**
 * @author Erwin, studentnummer 500889293
 * @version 9-12-2021
 */

@Service
public class FinancieelOverzichtService {

    private RootRepository rootRepository;

    public FinancieelOverzichtService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
    }

    public Klant vindFinancieelOverzicht(int klantId) {
        Klant klant = rootRepository.vindFinancieelOverzichtOpId(klantId);
        return klant;
    }
}
