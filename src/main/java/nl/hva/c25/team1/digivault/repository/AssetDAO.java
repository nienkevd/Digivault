package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Asset;

import java.util.List;

/**
 * @Author: Erwin, studentnummer 500889293
 * @Version: 1-12-2021
 *
 * Interface met de te implementeren methodes voor JdbcAssetDAO
 */

public interface AssetDAO {

    public void save(Asset asset);

    public Asset findAssetByAfkorting(String afkorting);

    public List<Asset> findAllAssets();

}
