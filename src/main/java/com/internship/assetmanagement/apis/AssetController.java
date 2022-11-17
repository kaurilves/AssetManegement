package com.internship.assetmanagement.apis;

import com.internship.assetmanagement.dtos.asset.Asset;
import com.internship.assetmanagement.dtos.asset.AssetCreate;
import com.internship.assetmanagement.dtos.asset.AssetUpdate;
import com.internship.assetmanagement.services.user.AccessManager;
import com.internship.assetmanagement.services.asset.AssetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/asset")
public class AssetController {

    @Resource
    private AssetService assetService;

    @Resource
    private AccessManager accessManager;

    @PostMapping
    public Asset addAsset(@RequestBody AssetCreate assetCreate) throws Exception {
        var loggedInUser = accessManager.getLoggedInUser();
        return assetService.addAsset(loggedInUser, assetCreate);
    }

    @GetMapping("/{assetId}")
    public Asset getAsset(@PathVariable Integer assetId) throws Exception {
        return assetService.getAsset(assetId);
    }

    @GetMapping("/{userCompanyId}")
    public List<Asset> getAllAssets(@PathVariable Integer userCompanyId) {
        return assetService.getAllAssets(userCompanyId);
    }

    @PutMapping("/{assetId}")
    public Asset updateAsset(@RequestBody AssetUpdate assetUpdate) throws Exception {
        var loggedInUser = accessManager.getLoggedInUser();
        return assetService.updateAsset(loggedInUser, assetUpdate);
    }

    @DeleteMapping
    public void deleteAsset(@PathVariable Integer assetId) {
        assetService.deleteAsset(assetId);
    }

    @PostMapping("/checkin")
    public void setAssetAsCheckedInOrCheckedOut(Integer assetId, String comment, Boolean isCheckedIn) throws Exception {
        var loggedInUser = accessManager.getLoggedInUser();
        assetService.setAssetAsCheckedInOrCheckedOut(assetId, loggedInUser, comment, isCheckedIn);
    }


}
