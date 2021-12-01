package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Asset;

import java.util.List;

// review door Sezi, 1 december

/**
 * @author Erwin, studentnummer 500889293
 * @version 1-12-2021
 *
 * Interface met de te implementeren methodes voor JdbcAssetDAO
 */

public interface AssetDAO {

    public void bewaar(Asset asset);

    public Asset vindAssetOpAfkorting(String afkorting);

    public List<Asset> geefAlleAssets();

    public void ververs(Asset asset);
}
