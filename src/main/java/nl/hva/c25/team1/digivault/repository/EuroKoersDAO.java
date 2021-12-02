package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.EuroKoers;

import java.util.List;

/**
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 *
 * Interface met de te implementeren methodes voor JdbcAssetDAO
 */

public interface EuroKoersDAO {

    public void bewaar(EuroKoers euroKoers);

    public EuroKoers vindEuroKoersOpId(int euroKoersId);

    public List<EuroKoers> geefAlleEuroKoersen();

    public void ververs(EuroKoers euroKoers);
}
