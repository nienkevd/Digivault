package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Bank;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Rekening;
import nl.hva.c25.team1.digivault.model.Transactie;
import nl.hva.c25.team1.digivault.repository.JdbcRekeningDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// review door Erwin, 1 december

/**
 * @author Sezi, studentnummer 500889525
 * @version 1-12-2021
 */
@Service
public class RekeningService {

    private JdbcRekeningDAO jdbcRekeningDAO;

    @Autowired
    public RekeningService(JdbcRekeningDAO rekeningDAO) {
        this.jdbcRekeningDAO = rekeningDAO;
    }

    public void bewaarRekening(Rekening rekening) {
        jdbcRekeningDAO.bewaar(rekening);
    }

    public String updateRekening(Rekening rekening) {
        if (jdbcRekeningDAO.vindRekeningOpIBAN(rekening.getIBAN()) == null) {
            return "Rekening bestaat niet, update mislukt.";
        } else {
            jdbcRekeningDAO.updateRekening(rekening);
            return "Update geslaagd";
        }
    }

    public Rekening vindRekeningOpIBAN(String IBAN) {
        return jdbcRekeningDAO.vindRekeningOpIBAN(IBAN);
    }

    public Rekening vindRekeningOpId(int rekeningId) {
        return jdbcRekeningDAO.vindRekeningOpId(rekeningId);
    }

    public List<Rekening> geefAlleRekeningen() {
        return jdbcRekeningDAO.geefAlleRekeningen();
    }

}
