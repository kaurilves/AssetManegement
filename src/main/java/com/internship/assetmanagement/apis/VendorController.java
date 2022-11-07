package com.internship.assetmanagement.apis;

import com.internship.assetmanagement.dtos.*;
import com.internship.assetmanagement.services.CustomFieldService;
import com.internship.assetmanagement.services.VendorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Resource
    private VendorService vendorService;

    @Resource
    private CustomFieldService customFieldService;

    @PostMapping
    public Vendor addVendor(@Valid @RequestBody VendorCreate vendorCreate){
        return  vendorService.addVendor(vendorCreate);
    }

    @GetMapping("/{id}")
    public Vendor getVendor(@PathVariable Integer vendorId){
        return  vendorService.getVendor(vendorId);
    }

    @GetMapping
    public List<Vendor> searchVendors(String searchWord){
        return  vendorService.searchVendors(searchWord);
    }

    @PatchMapping
    public Vendor updateVendor(@RequestBody VendorUpdate vendorUpdate){
        return  vendorService.updateVendor(vendorUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteVendor(@PathVariable Integer vendorId){
        vendorService.deleteVendor(vendorId);
    }

    @PostMapping("/custom_field/{id}")
    public void createVendorCustomField(@PathVariable Integer vendorId, @Valid @RequestBody CustomFieldCreate customFieldCreate){
          vendorService.createVendorCustomField(vendorId, customFieldCreate);
    }

    @PatchMapping("/custom_field")
    public void updateCustomField(@Valid @RequestBody CustomFieldUpdate customFieldUpdate){
        customFieldService.updateCustomField(customFieldUpdate);
    }

    @DeleteMapping("/custom_field/{id}")
    public void deleteVendorCustomField(@PathVariable Integer vendorCustomFieldId){
        vendorService.deleteVendorCustomField(vendorCustomFieldId);
    }

}
