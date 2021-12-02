package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.JdbcKlantDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlantService {

    private JdbcKlantDAO klantDAO;
    private RootRepository rootRepository;

    /**
     *
     * @param klantDAO interface klantDAO
     * @param rootRepository rootrepo
     */
    public KlantService(JdbcKlantDAO klantDAO, RootRepository rootRepository) {
        this.klantDAO = klantDAO;
        this.rootRepository = rootRepository;
    }

    /**
     *
     * @param klant opslaan
     */
    public void bewaarKlant(Klant klant) {
        klantDAO.bewaar(klant);
    }


    /**
     *
     * @param klandId van klant die gevonden moet worden
     * @return Klant
     */
    public Klant vindKlantOpKlantID(int klandId) {

        return klantDAO.vindKlantOpKlantId(klandId);
    }

    /**
     *
     * @return List<Klant>
     */
    public List<Klant> vindAlleKlanten() {
        return klantDAO.vindAlleKlanten();
    }






}
