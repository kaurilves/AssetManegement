package com.internship.assetmanagement;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VendorMapper {

    VendorEntity vendorCreateToVendorEntity(VendorCreate vendorCreate);

    Vendor vendorEntityToVendor(VendorEntity vendorEntity);
}
