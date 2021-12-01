package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Asset;

import java.util.List;

public interface AssetDAO {

    public void save(Asset asset);

    public Asset findAssetByAfkorting(String afkorting);

    public List<Asset> findAllAssets();

    public void update(Asset asset);
}
