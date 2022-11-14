package com.internship.assetmanagement.apis;

import com.internship.assetmanagement.dtos.asset.Asset;
import com.internship.assetmanagement.dtos.asset.AssetCreate;
import com.internship.assetmanagement.services.asset.AssetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/asset")
public class AssetController {

    @Resource
    AssetService assetService;

    @PostMapping
    public Asset addAsset(@RequestBody AssetCreate assetCreate){
        return  assetService.addAsset(assetCreate);
    }

}
