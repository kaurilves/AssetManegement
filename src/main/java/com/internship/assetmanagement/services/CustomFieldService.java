package com.internship.assetmanagement.services;

import com.internship.assetmanagement.dtos.CustomField;
import com.internship.assetmanagement.dtos.CustomFieldCreate;
import com.internship.assetmanagement.dtos.CustomFieldUpdate;
import com.internship.assetmanagement.entities.vendor.CustomFieldEntity;
import com.internship.assetmanagement.mappers.CustomFieldMapper;
import com.internship.assetmanagement.repositories.CustomFieldRepository;
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


