package com.internship.assetmanagement.services.vendor;

import com.internship.assetmanagement.dtos.vendor.CustomField;
import com.internship.assetmanagement.dtos.vendor.CustomFieldCreate;
import com.internship.assetmanagement.dtos.vendor.CustomFieldUpdate;
import com.internship.assetmanagement.entities.vendor.CustomFieldEntity;
import com.internship.assetmanagement.mappers.vendor.CustomFieldMapper;
import com.internship.assetmanagement.repositories.vendor.CustomFieldRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomFieldService {

    @Resource
    private CustomFieldMapper customFieldMapper;

    @Resource
    private CustomFieldRepository customFieldRepository;

    public CustomField addCustomField(CustomFieldCreate customFieldCreate){
        CustomFieldEntity customFieldEntity = customFieldMapper.customFieldCreateToCustomFieldEntity (customFieldCreate);
        customFieldRepository.save(customFieldEntity);
        return customFieldMapper.customFieldEntityToCustomField(customFieldEntity);
    }

    public CustomField getCustomField(Integer id){
        CustomFieldEntity customFieldEntity =  customFieldRepository.findById(id).get();
        return customFieldMapper.customFieldEntityToCustomField(customFieldEntity);
    }

    public CustomField updateCustomField(CustomFieldUpdate customFieldUpdate){
        CustomFieldEntity customFieldEntity =  customFieldRepository.findById(customFieldUpdate.getId()).get();
        customFieldEntity.setName(customFieldUpdate.getName());
        customFieldEntity.setValue(customFieldUpdate.getValue());
        customFieldRepository.save(customFieldEntity);
        return customFieldMapper.customFieldEntityToCustomField(customFieldEntity);
    }

    public void deleteCustomField(Integer id){
        customFieldRepository.deleteById(id);
    }
}


