package com.internship.assetmanagement.mappers.vendor;

import com.internship.assetmanagement.dtos.vendor.CustomField;
import com.internship.assetmanagement.dtos.vendor.CustomFieldCreate;
import com.internship.assetmanagement.entities.vendor.CustomFieldEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomFieldMapper {
    
    CustomFieldEntity customFieldCreateToCustomFieldEntity(CustomFieldCreate customFieldCreate);

    CustomField customFieldEntityToCustomField(CustomFieldEntity customFieldEntity);
}
