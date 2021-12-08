package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.KlantDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.stereotype.Service;

/**
 * @author Erwin, studentnummer 500889293
 */

@Service
public class RegistratieService {

    private RootRepository rootRepository;

    public RegistratieService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
    }
}
