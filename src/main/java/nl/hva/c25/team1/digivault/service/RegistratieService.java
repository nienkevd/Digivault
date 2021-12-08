package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.KlantDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.stereotype.Service;

/**
 * @author Erwin, studentnummer 500889293
 * @since 7-12-2021
 */

@Service
public class RegistratieService {

    private KlantDAO klantDAO;
    private RootRepository rootRepository;

    public RegistratieService(KlantDAO klantDAO, RootRepository rootRepository) {
        super();
        this.klantDAO = klantDAO;
        this.rootRepository = rootRepository;
    }
}
