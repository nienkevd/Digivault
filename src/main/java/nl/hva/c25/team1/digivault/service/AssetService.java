package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.repository.JdbcAssetDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;

import java.util.List;

/**
 * @Author: Erwin, studentnummer 500889293
 * @Version: 1-12-2021
 */

public class AssetService {

    private JdbcAssetDAO jdbcAssetDAO;
    private RootRepository rootRepository;

    public AssetService(JdbcAssetDAO jdbcAssetDAO, RootRepository rootRepository) {
        super();
        this.jdbcAssetDAO = jdbcAssetDAO;
        this.rootRepository = rootRepository;
    }

    public void bewaarAsset(Asset asset) {
        jdbcAssetDAO.bewaar(asset);
    }

    public Asset vindAssetOpAfkorting(String afkorting) {
        return jdbcAssetDAO.vindAssetOpAfkorting(afkorting);
    }

    public List<Asset> geefAlleAssets() {
        return jdbcAssetDAO.geefAlleAssets();
    }

    public String verversAsset(Asset asset) {
        if (jdbcAssetDAO.vindAssetOpAfkorting(asset.getAfkorting()) == null) {
            return "Asset is niet gevonden, het verversen is mislukt.";
        } else{
            jdbcAssetDAO.ververs(asset);
            return "Asset is ververst.";
        }
    }
}
