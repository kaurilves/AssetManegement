package com.internship.assetmanagement.repositories;

import com.internship.assetmanagement.entities.vendor.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Integer> {
    List<VendorEntity> findByCompanyNameContainsOrAddressContainsOrWebsiteContainsOrContactNameContainsOrEmailContainsOrVendorTypeContainsOrDescriptionContains
            (String companyName, String address, String website, String contactName, String email, String vendorType, String description);




}
