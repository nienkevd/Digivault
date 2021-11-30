package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Klant;

import java.util.List;

public interface KlantDAO {

    public void save(Klant klant);

    public Klant findKlantById(int klantnummer);

    public List<Klant> findAlleKlanten();

}
