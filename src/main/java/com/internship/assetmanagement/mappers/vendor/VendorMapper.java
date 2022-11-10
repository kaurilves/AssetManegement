package com.internship.assetmanagement.mappers.vendor;

import com.internship.assetmanagement.entities.vendor.VendorEntity;
import com.internship.assetmanagement.dtos.vendor.Vendor;
import com.internship.assetmanagement.dtos.vendor.VendorCreate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VendorMapper {

    VendorEntity vendorCreateToVendorEntity(VendorCreate vendorCreate);

    Vendor vendorEntityToVendor(VendorEntity vendorEntity);

    List<Vendor> vendorEntitiesToVendors(List<VendorEntity> vendorEntities);
}
