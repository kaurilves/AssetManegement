package com.internship.assetmanagement.mappers;

import com.internship.assetmanagement.dtos.CustomField;
import com.internship.assetmanagement.dtos.CustomFieldCreate;
import com.internship.assetmanagement.entities.CustomFieldEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomFieldMapper {
    
    CustomFieldEntity customFieldCreateToCustomFieldEntity(CustomFieldCreate customFieldCreate);

    CustomField customFieldEntityToCustomField(CustomFieldEntity customFieldEntity);
}
