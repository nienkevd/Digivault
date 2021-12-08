package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.JdbcKlantDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service van de klasse Klant
 *
 * @author Anneke, studentnummer 500889251
 * @version 1-12-2021
 */

@Service
public class KlantService {

    private JdbcKlantDAO klantDAO;
    private RootRepository rootRepository;

    /**
     *
     * @param klantDAO interface klantDAO
     */
    public KlantService(JdbcKlantDAO klantDAO /* , RootRepository rootRepository*/) {
        this.klantDAO = klantDAO;
//        this.rootRepository = rootRepository;
    }

    /**
     *
     * @param klant opslaan
     */
    public Klant bewaarKlant(Klant klant) {
        return klantDAO.bewaarKlantMetSK(klant);
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

    /**
     *
     * update een bestaande klant uit de database
     * @param klant die geupdate moet worden
     * @return String melding die aangeeft of update geslaagd is
     */
    public String updateKlant(Klant klant) {
        if (klantDAO.vindKlantOpKlantId(klant.getKlantId()) == null ) {
            return "Klant bestaat niet, update mislukt.";
        } else {
            klantDAO.update(klant);
            return "Update geslaagd";
        }
    }






}
