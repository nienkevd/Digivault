package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Naam;

import java.util.List;

public interface NaamDAO {

    void bewaar(Naam naam);

    Naam vindNaamOpNaamId(int naamId);

    List<Naam> vindAlleNamen();

    void update(Naam naam);



}
