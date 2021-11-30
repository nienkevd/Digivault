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

    public KlantService(JdbcKlantDAO klantDAO, RootRepository rootRepository) {
        this.klantDAO = klantDAO;
        this.rootRepository = rootRepository;
    }

    public void saveKlant(Klant klant) {
        klantDAO.save(klant);
    }

    public Klant getKlantById(int klantId) {

        return klantDAO.findKlantById(klantId);
    }

    public List<Klant> getAlleKlanten() {
        return klantDAO.findAlleKlanten();
    }






}
