package com.internship.assetmanagement;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomFieldMapper {
    
    CustomFieldEntity customFieldCreateToCustomFieldEntity(CustomFieldCreate customFieldCreate);

    CustomField customFieldEntityToCustomField(CustomFieldEntity customFieldEntity);
}
