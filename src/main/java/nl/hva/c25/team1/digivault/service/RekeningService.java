package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Rekening;
import nl.hva.c25.team1.digivault.repository.JdbcRekeningDAO;
import nl.hva.c25.team1.digivault.repository.RekeningDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;

import java.util.List;

// review door Erwin, 1 december: eventueel nog auteursinfo toevoegen

public class RekeningService {

    private JdbcRekeningDAO rekeningDAO;
    private RootRepository rootRepository;

    public RekeningService(JdbcRekeningDAO rekeningDAO, RootRepository rootRepository) {
        this.rekeningDAO = rekeningDAO;
        this.rootRepository = rootRepository;
    }

    public void bewaar(Rekening rekening) {
        rekeningDAO.bewaar(rekening);
    }

    public void ververs(Rekening rekening) {
        rekeningDAO.ververs(rekening);
    }

    public Rekening vindRekeningOpIBAN(String IBAN) {
        return rekeningDAO.vindRekeningOpIBAN(IBAN);
    }

    public List<Rekening> geefAlleRekeningen() {
        return rekeningDAO.geefAlleRekeningen();
    }
}
