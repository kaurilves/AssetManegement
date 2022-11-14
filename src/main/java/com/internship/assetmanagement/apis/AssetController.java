package com.internship.assetmanagement.apis;

import com.internship.assetmanagement.dtos.asset.AssetCreate;
import com.internship.assetmanagement.dtos.asset.AssetResponse;
import com.internship.assetmanagement.services.AccessManager;
import com.internship.assetmanagement.services.asset.AssetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/asset")
public class AssetController {

    @Resource
    private AssetService assetService;

    @Resource
    private AccessManager accessManager;

    @PostMapping
    public AssetResponse addAsset(@RequestBody AssetCreate assetCreate) throws Exception {
        var loggedInUser = accessManager.getLoggedInUser();
        return  assetService.addAsset(loggedInUser, assetCreate);
    }

}
