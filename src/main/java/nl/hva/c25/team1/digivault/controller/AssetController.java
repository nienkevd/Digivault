package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.service.AssetService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// review door Sezi, 1 december

/**
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 */

public class AssetController {

    private AssetService assetService;

    /**
     * Constructor van de AssetController
     * @param assetService
     */
    public AssetController(AssetService assetService) {
        super();
        this.assetService = assetService;
    }

    /**
     *
     * @param asset de te bewaren asset
     */
    @PostMapping("/assets")
    public void bewaar(@RequestBody Asset asset) {
        assetService.bewaarAsset(asset);
    }

    /**
     *
     * @param assetId het id waarmee de asset gevonden moet worden
     * @return
     */
    @PostMapping("/assets/{assetId}")
    public Asset vindAssetOpId(@PathVariable int assetId) {
        return assetService.vindAssetOpId(assetId);
    }

    /**
     *
     * @return
     */
    @PostMapping("/assets")
    public List<Asset> geefAlleAssets() {
        return assetService.geefAlleAssets();
    }

    /**
     *
     * @param asset de te verversen asset
     * @return
     */
    @PostMapping("/assets/{assetId}")
    public String ververs(@RequestBody Asset asset) {
        return assetService.verversAsset(asset);
    }
}
