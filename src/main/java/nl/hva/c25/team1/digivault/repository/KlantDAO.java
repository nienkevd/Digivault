package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;

import java.util.List;

public interface KlantDAO {

    void bewaar(Klant klant);

    Klant vindKlantOpKlantId(int klantId);

    List<Klant> vindAlleKlanten();

    void update(Klant klant);

}
