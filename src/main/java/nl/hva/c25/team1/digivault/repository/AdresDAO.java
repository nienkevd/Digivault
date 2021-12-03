package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Adres;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Naam;

import java.util.List;

public interface AdresDAO {

    int bewaarAdresMetSK(Adres adres);

    Adres vindAdresOpAdresId(int adresId);

    List<Adres> vindAlleAdressen();

    void update(Adres adres);
}
