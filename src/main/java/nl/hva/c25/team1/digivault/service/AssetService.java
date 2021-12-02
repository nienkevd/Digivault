package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.repository.JdbcAssetDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;

import java.util.List;

// review door Sezi, 1 december


/**
 * @author Erwin, studentnummer 500889293
 * @version 1-12-2021
 */

public class AssetService {

    private JdbcAssetDAO jdbcAssetDAO;
    private RootRepository rootRepository;

    /**
     * Constructor van de AssetService
     * @param jdbcAssetDAO
     * @param rootRepository
     */
    public AssetService(JdbcAssetDAO jdbcAssetDAO, RootRepository rootRepository) {
        super();
        this.jdbcAssetDAO = jdbcAssetDAO;
        this.rootRepository = rootRepository;
    }

    /**
     * Sla een nieuwe asset op
     * @param asset die opgeslagen moet worden
     */
    public void bewaarAsset(Asset asset) {
        jdbcAssetDAO.bewaar(asset);
    }

    /**
     * Geeft een asset op basis van id terug
     * @param assetId waarop gezocht wordt
     * @return de bijbehorende asset
     */
    public Asset vindAssetOpId(int assetId) {
        return jdbcAssetDAO.vindAssetOpId(assetId);
    }

    /**
     * Geeft een lijst van alle assets terug
     * @return lijst met alle assets
     */
    public List<Asset> geefAlleAssets() {
        return jdbcAssetDAO.geefAlleAssets();
    }

    /**
     * Ververst een bestaande asset
     * @param asset welke geüpdate moet worden
     * @return String-melding of verversen gelukt is
     */
    public String verversAsset(Asset asset) {
        if (jdbcAssetDAO.vindAssetOpId(asset.getAssetId()) == null) {
            return "Asset is niet gevonden, het verversen is mislukt.";
        } else {
            jdbcAssetDAO.ververs(asset);
            return "Asset is ververst.";
        }
    }
}
