package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Rekening;
import nl.hva.c25.team1.digivault.repository.JdbcRekeningDAO;
import nl.hva.c25.team1.digivault.repository.RekeningDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;

import java.util.List;

// review door Erwin, 1 december

/**
 * @author Sezi, studentnummer 500889525
 * @version 1-12-2021
 */

public class RekeningService {

    private JdbcRekeningDAO rekeningDAO;
    private RootRepository rootRepository;

    public RekeningService(JdbcRekeningDAO rekeningDAO/*, RootRepository rootRepository*/) {
        this.rekeningDAO = rekeningDAO;
        this.rootRepository = rootRepository;
    }

    public void bewaarRekening(Rekening rekening) {

        rekeningDAO.bewaar(rekening);
    }

    public String updateRekening(Rekening rekening) {
            if (rekeningDAO.vindRekeningOpIBAN(rekening.getIBAN()) == null ) {
                return "Rekening bestaat niet, update mislukt.";
            } else {
                rekeningDAO.updateRekening(rekening);
                return "Update geslaagd";
            }
    }

    public Rekening vindRekeningOpIBAN(String IBAN) {

        return rekeningDAO.vindRekeningOpIBAN(IBAN);
    }

    public List<Rekening> geefAlleRekeningen() {

        return rekeningDAO.geefAlleRekeningen();
    }
}
