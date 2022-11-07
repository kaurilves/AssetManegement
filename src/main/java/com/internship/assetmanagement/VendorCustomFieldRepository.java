package com.internship.assetmanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorCustomFieldRepository extends JpaRepository<VendorCustomFieldEntity, Integer> {
    List<VendorCustomFieldEntity> findByVendorsId(Integer vendorsId);

}
