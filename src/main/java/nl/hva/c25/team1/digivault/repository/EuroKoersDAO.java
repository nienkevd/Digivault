package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.EuroKoers;

import java.util.List;

/**
 * Interface met de te implementeren methodes voor JdbcAssetDAO
 *
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 */

public interface EuroKoersDAO {

    void bewaar(EuroKoers euroKoers);

    int bewaarEuroKoersMetSK(EuroKoers euroKoers);

    EuroKoers vindEuroKoersOpId(int euroKoersId);

    List<EuroKoers> geefAlleEuroKoersen();

    void ververs(EuroKoers euroKoers);
}
