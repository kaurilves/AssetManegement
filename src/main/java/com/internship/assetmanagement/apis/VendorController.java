package com.internship.assetmanagement.apis;

import com.internship.assetmanagement.dtos.vendor.*;
import com.internship.assetmanagement.services.AccessManager;
import com.internship.assetmanagement.services.vendor.CustomFieldService;
import com.internship.assetmanagement.services.vendor.VendorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Resource
    private VendorService vendorService;

    @Resource
    private CustomFieldService customFieldService;

    @Resource
    private AccessManager accessManager;

    @PostMapping
    public Vendor addVendor(@RequestBody VendorCreate vendorCreate){
        var loggedInUser = accessManager.getLoggedInUser();
        return  vendorService.addVendor(vendorCreate);
    }

    @GetMapping("/{vendorId}")
    public Vendor getVendor(@PathVariable Integer vendorId) {
        return  vendorService.getVendor(vendorId);
    }

    @GetMapping("/vendors")
    public List<Vendor> getAllVendors(){
        return  vendorService.getAllVendors();
    }

    @GetMapping("/vendors/{keyword}")
    public List<Vendor> searchVendors(@PathVariable String keyword){
        return  vendorService.searchVendors(keyword);
    }

    @PatchMapping
    public Vendor updateVendor(@RequestBody VendorUpdate vendorUpdate){
        return  vendorService.updateVendor(vendorUpdate);
    }

    @DeleteMapping("/{vendorId}")
    public void deleteVendor(@PathVariable Integer vendorId){
        vendorService.deleteVendor(vendorId);
    }

    @PostMapping("/custom_field/{vendorId}")
    public void createVendorCustomField(@PathVariable Integer vendorId, @RequestBody CustomFieldCreate customFieldCreate){
          vendorService.createVendorCustomField(vendorId, customFieldCreate);
    }

    @PatchMapping("/custom_field")
    public void updateCustomField(@RequestBody CustomFieldUpdate customFieldUpdate){
        customFieldService.updateCustomField(customFieldUpdate);
    }

    @DeleteMapping("/custom_field/{customFieldId}")
    public void deleteVendorCustomField(@PathVariable Integer customFieldId){
        vendorService.deleteVendorCustomField(customFieldId);
    }

}
