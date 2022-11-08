package com.internship.assetmanagement.services;

import com.internship.assetmanagement.dtos.VendorUpdate;
import com.internship.assetmanagement.dtos.CustomField;
import com.internship.assetmanagement.dtos.CustomFieldCreate;
import com.internship.assetmanagement.dtos.Vendor;
import com.internship.assetmanagement.dtos.VendorCreate;
import com.internship.assetmanagement.entities.VendorCustomFieldEntity;
import com.internship.assetmanagement.entities.VendorEntity;
import com.internship.assetmanagement.mappers.VendorMapper;
import com.internship.assetmanagement.repositories.VendorCustomFieldRepository;
import com.internship.assetmanagement.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendorService {

    @Resource
    private VendorMapper vendorMapper;

    @Resource
    private VendorRepository vendorRepository;

    @Resource
    private VendorCustomFieldRepository vendorCustomFieldRepository;

    @Resource
    private CustomFieldService customFieldService;

    public Vendor addVendor (VendorCreate vendorCreate){
        VendorEntity vendorEntity = vendorMapper.vendorCreateToVendorEntity(vendorCreate);
        vendorEntity.setDateCreated(LocalDate.now());
        vendorRepository.save(vendorEntity);
        Vendor vendor = vendorMapper.vendorEntityToVendor(vendorEntity);
        vendor.setCustomFields(addCustomFieldInfoToVendor(vendor.getId()));
        return vendor;
    }

    public Vendor getVendor(Integer id){
        VendorEntity vendorEntity = vendorRepository.findById(id).get();
        Vendor vendor = vendorMapper.vendorEntityToVendor(vendorEntity);
        vendor.setCustomFields(addCustomFieldInfoToVendor(vendor.getId()));
        return vendor;
    }

    public List<Vendor> getAllVendors() {
        List<VendorEntity> vendorEntities = vendorRepository.findAll();
        return vendorMapper.vendorEntitiesToVendors(vendorEntities);
    }

    public List<Vendor> searchVendors(String searchWord) {
        List<VendorEntity> vendorEntities = vendorRepository.
                findByCompanyNameContainsOrAddressContainsOrWebsiteContainsOrContactNameContainsOrEmailContainsOrVendorTypeContainsOrDescriptionContains
                        (searchWord, searchWord, searchWord, searchWord, searchWord, searchWord, searchWord);
        return vendorMapper.vendorEntitiesToVendors(vendorEntities);
    }

    public Vendor updateVendor (VendorUpdate vendorUpdate){
        VendorEntity vendorEntity = vendorRepository.findById(vendorUpdate.getId()).get();
        vendorEntity.setCompanyName(vendorUpdate.getCompanyName());
        vendorEntity.setAddress(vendorUpdate.getAddress());
        vendorEntity.setWebsite(vendorUpdate.getWebsite());
        vendorEntity.setContactName(vendorUpdate.getContactName());
        vendorEntity.setEmail(vendorUpdate.getEmail());
        vendorEntity.setVendorType(vendorUpdate.getVendorType());
        vendorEntity.setDescription(vendorUpdate.getDescription());
        vendorEntity.setRate(vendorUpdate.getRate());
        vendorRepository.save(vendorEntity);
        Vendor vendor = vendorMapper.vendorEntityToVendor(vendorEntity);
        vendor.setCustomFields(addCustomFieldInfoToVendor(vendor.getId()));
        return vendor;
    }

    public void deleteVendor(Integer id){
        deleteAllVendorCustomFields(id);
        vendorRepository.deleteById(id);
    }

    public void createVendorCustomField (Integer vendorId, CustomFieldCreate customFieldCreate){
        VendorCustomFieldEntity vendorCustomFieldEntity = new VendorCustomFieldEntity();
        vendorCustomFieldEntity.setVendorsId(vendorId);
        vendorCustomFieldEntity.setCustomFieldsId(customFieldService.addCustomField(customFieldCreate).getId());
        vendorCustomFieldRepository.save(vendorCustomFieldEntity);
    }

    public List<CustomField> addCustomFieldInfoToVendor(Integer vendorId){
        List<CustomField> customFields= new ArrayList<>();
        List<VendorCustomFieldEntity> vendorCustomFieldEntities = vendorCustomFieldRepository.findByVendorsId(vendorId);
        for (VendorCustomFieldEntity vendorCustomFieldEntity : vendorCustomFieldEntities){
            customFields.add(customFieldService.getCustomField(vendorCustomFieldEntity.getCustomFieldsId()));
        }
        return customFields;
    }


    public void deleteVendorCustomField (Integer customFieldId){
        vendorCustomFieldRepository.deleteByCustomFieldsId(customFieldId);
        customFieldService.deleteCustomField(customFieldId);
    }

    public void deleteAllVendorCustomFields (Integer vendorId){
        List<VendorCustomFieldEntity> vendorCustomFieldEntities = vendorCustomFieldRepository.findByVendorsId(vendorId);
        for (VendorCustomFieldEntity vendorCustomFieldEntity : vendorCustomFieldEntities){
            Integer customFieldId = vendorCustomFieldEntity.getCustomFieldsId();
            vendorCustomFieldRepository.deleteById(vendorCustomFieldEntity.getId());
            customFieldService.deleteCustomField(customFieldId);
        }
    }



}


