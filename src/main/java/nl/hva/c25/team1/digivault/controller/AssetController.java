package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.service.AssetService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Erwin, studentnummer 500889293
 * @version 1-12-2021
 */

public class AssetController {

    private AssetService assetService;

    public AssetController(AssetService assetService) {
        super();
        this.assetService = assetService;
    }

    @PostMapping("/assets")
    public void bewaarAsset(@RequestBody Asset asset) {
        assetService.bewaarAsset(asset);
    }

    @PostMapping("/assets/{afkorting}")
    public Asset vindAssetOpAfkorting(@PathVariable String afkorting) {
        return assetService.vindAssetOpAfkorting(afkorting);
    }

    @PostMapping("/assets")
    public List<Asset> geefAlleAssets() {
        return assetService.geefAlleAssets();
    }
}
