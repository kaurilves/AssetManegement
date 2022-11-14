package com.internship.assetmanagement.mappers.asset;

import com.internship.assetmanagement.dtos.asset.AssetCreate;
import com.internship.assetmanagement.entities.asset.AssetEntity;
import com.internship.assetmanagement.dtos.asset.AssetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AssetMapper {

    @Mapping(target = "userCompany.id", source = "userCompanyId")
    @Mapping(target = "creatorUser.id", source = "creatorUserId")
    @Mapping(target = "parentAsset.id", source = "parentAssetId")
    @Mapping(target = "location.id", source = "locationId")
    AssetEntity assetCreateToAssetEntity(AssetCreate assetCreate);


    AssetResponse assetEntityToAssetResponse(AssetEntity assetEntity);
}
