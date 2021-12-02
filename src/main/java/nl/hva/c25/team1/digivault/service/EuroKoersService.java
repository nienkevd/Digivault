package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.EuroKoers;
import nl.hva.c25.team1.digivault.repository.EuroKoersDAO;
import nl.hva.c25.team1.digivault.repository.JdbcEuroKoersDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;

import java.util.List;

/**
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 */

public class EuroKoersService {

    private JdbcEuroKoersDAO jdbcEuroKoersDAO;
    private RootRepository rootRepository;

    public EuroKoersService(JdbcEuroKoersDAO jdbcEuroKoersDAO, RootRepository rootRepository) {
        super();
        this.jdbcEuroKoersDAO = jdbcEuroKoersDAO;
        this.rootRepository = rootRepository;
    }

    public void bewaarEuroKoers(EuroKoers euroKoers) {
        jdbcEuroKoersDAO.bewaar(euroKoers);
    }

    public EuroKoers vindEuroKoersOpId(int euroKoersId) {
        return jdbcEuroKoersDAO.vindEuroKoersOpId(euroKoersId);
    }

    public List<EuroKoers> geefAlleEuroKoersen() {
        return jdbcEuroKoersDAO.geefAlleEuroKoersen();
    }

    public String verversEuroKoers(EuroKoers euroKoers) {
        if (jdbcEuroKoersDAO.vindEuroKoersOpId(euroKoers.getEuroKoersId()) == null) {
            return "Eurokoers is niet gevonden, het verversen is mislukt.";
        } else {
            jdbcEuroKoersDAO.ververs(euroKoers);
            return "Eurokoers is ververst.";
        }
    }
}
