package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Adres;
import nl.hva.c25.team1.digivault.model.Naam;

import java.util.List;

public interface AdresDAO {

    Adres vindAdresOpAdresId(int adresId);

    List<Adres> vindAlleAdressen();

    void update(Adres adres);
}
