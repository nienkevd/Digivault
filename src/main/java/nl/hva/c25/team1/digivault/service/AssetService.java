package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.repository.JdbcAssetDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// review door Sezi, 1 december

/**
 * Service van de klasse Asset
 *
 * @author Erwin, studentnummer 500889293
 * @version 1-12-2021
 */

@Service
public class AssetService {

    private JdbcAssetDAO jdbcAssetDAO;
    private RootRepository rootRepository;

    /**
     * Constructor van de AssetService
     * @param jdbcAssetDAO JDBC Asset-DAO
     * @param rootRepository RootRepository
     */
    @Autowired
    public AssetService(JdbcAssetDAO jdbcAssetDAO, RootRepository rootRepository) {
        super();
        this.jdbcAssetDAO = jdbcAssetDAO;
        this.rootRepository = rootRepository;
    }

    /**
     * Slaat een nieuwe Asset op
     * @param asset de te bewaren Asset
     */
    public void bewaarAsset(Asset asset) {
        jdbcAssetDAO.bewaar(asset);
    }

    /**
     * Vindt Asset bij opgegeven assetId
     * @param assetId waarop Asset gezocht wordt
     * @return de bijbehorende Asset
     */
    public Asset vindAssetOpId(int assetId) {
        return jdbcAssetDAO.vindAssetOpId(assetId);
    }

    /**
     * Geeft een lijst van alle Assets terug
     * @return lijst met alle Assets
     */
    public List<Asset> geefAlleAssets() {
        return jdbcAssetDAO.geefAlleAssets();
    }

    /**
     * Ververst een bepaalde Asset
     * @param asset welke ververst moet worden
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
