package com.internship.assetmanagement.mappers.asset;

import com.internship.assetmanagement.dtos.asset.Asset;
import com.internship.assetmanagement.dtos.asset.AssetCreate;
import com.internship.assetmanagement.entities.asset.AssetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AssetMapper {


    @Mapping(target = "userCompany.id", source = "userCompanyId")
    //@Mapping(target = "parentAsset.id", source = "parentAssetId")
    //@Mapping(target = "location.id", source = "locationId")
    //@Mapping(target = "primaryUser.id", source = "primaryUserId")
    AssetEntity assetCreateToAssetEntity(AssetCreate assetCreate);

    @Named("location")
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "creatorUser", ignore = true)
    @Mapping(target = "primaryUser", ignore = true)
    @Mapping(target = "parentAsset", ignore = true)
    Asset assetEntityToAsset(AssetEntity assetEntity);
}
