package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Asset;

import java.util.List;

// review door Sezi, 1 december

/**
 * Interface met de te implementeren methodes voor JdbcAssetDAO
 *
 * @author Erwin, studentnummer 500889293
 * @version 6-12-2021
 */

public interface AssetDAO {

    int bewaarAssetMetSK(Asset asset);

    Asset vindAssetOpId(int assetId);

    List<Asset> geefAlleAssets();

    void update(Asset asset);
}
