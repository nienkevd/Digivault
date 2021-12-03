package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// review door Sezi, 1 december

/**
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 */

@RestController
public class AssetController {

    private AssetService assetService;

    /**
     * Constructor van de AssetController
     * @param assetService AssetService
     */
    @Autowired
    public AssetController(AssetService assetService) {
        super();
        this.assetService = assetService;
    }

    /**
     * Slaat een Asset op
     * @param asset de te bewaren Asset
     */
    @PostMapping("/assets")
    public void bewaar(@RequestBody Asset asset) {
        assetService.bewaarAsset(asset);
    }

    /**
     * Vindt Asset op assetId
     * @param assetId waarop Asset gezocht wordt
     * @return doorgeefluik
     */
    @PostMapping("/assets/{assetId}")
    public Asset vindAssetOpId(@PathVariable int assetId) {
        return assetService.vindAssetOpId(assetId);
    }

    /**
     * Geeft een lijst van alle Assets terug
     * @return doorgeefluik
     */
    @PostMapping("/assets")
    public List<Asset> geefAlleAssets() {
        return assetService.geefAlleAssets();
    }

    /**
     * Ververst een bepaalde Asset
     * @param asset welke ververst moet worden
     * @return doorgeefluik
     */
    @PostMapping("/assets/{assetId}")
    public String ververs(@RequestBody Asset asset) {
        return assetService.verversAsset(asset);
    }
}
