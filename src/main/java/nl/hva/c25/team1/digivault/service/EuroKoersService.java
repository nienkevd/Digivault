package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.EuroKoers;
import nl.hva.c25.team1.digivault.repository.JdbcEuroKoersDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service van de klasse EuroKoers
 *
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 */

@Service
public class EuroKoersService {

    private JdbcEuroKoersDAO jdbcEuroKoersDAO;
    private RootRepository rootRepository;

    /**
     * Constructor van de EuroKoersService
     * @param jdbcEuroKoersDAO JDBC EuroKoers-DAO
     * @param rootRepository RootRepository
     */
    @Autowired
    public EuroKoersService(JdbcEuroKoersDAO jdbcEuroKoersDAO, RootRepository rootRepository) {
        super();
        this.jdbcEuroKoersDAO = jdbcEuroKoersDAO;
        this.rootRepository = rootRepository;
    }

    /**
     * Slaat een nieuwe EuroKoers op
     * @param euroKoers de te bewaren EuroKoers
     */
    public void bewaarEuroKoers(EuroKoers euroKoers) {
        jdbcEuroKoersDAO.bewaar(euroKoers);
    }

    /**
     * Vindt EuroKoers bij opgegeven euroKoersId
     * @param euroKoersId waarop EuroKoers gezocht wordt
     * @return de bijbehorende Asset
     */
    public EuroKoers vindEuroKoersOpId(int euroKoersId) {
        return jdbcEuroKoersDAO.vindEuroKoersOpId(euroKoersId);
    }

    /**
     * Geeft een lijst van alle EuroKoersen
     * @return lijst met alle EuroKoersen
     */
    public List<EuroKoers> geefAlleEuroKoersen() {
        return jdbcEuroKoersDAO.geefAlleEuroKoersen();
    }

    /**
     * Ververst een bepaalde EuroKoers
     * @param euroKoers welke ververst moet worden
     * @return String-melding of verversen gelukt is
     */
    public String verversEuroKoers(EuroKoers euroKoers) {
        if (jdbcEuroKoersDAO.vindEuroKoersOpId(euroKoers.getEuroKoersId()) == null) {
            return "Eurokoers is niet gevonden, het verversen is mislukt.";
        } else {
            jdbcEuroKoersDAO.ververs(euroKoers);
            return "Eurokoers is ververst.";
        }
    }
}
