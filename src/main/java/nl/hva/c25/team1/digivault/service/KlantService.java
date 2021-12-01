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

    public void bewaarKlant(Klant klant) {
        klantDAO.bewaar(klant);
    }

    public Klant vindKlantOpGebruikersnaam(String gebruikersnaam) {

        return klantDAO.vindKlantOpGebruikersnaam(gebruikersnaam);
    }

    public List<Klant> vindAlleKlanten() {
        return klantDAO.vindAlleKlanten();
    }






}
