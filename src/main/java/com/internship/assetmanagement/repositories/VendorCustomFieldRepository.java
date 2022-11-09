package com.internship.assetmanagement.repositories;

import com.internship.assetmanagement.entities.vendor.VendorCustomFieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VendorCustomFieldRepository extends JpaRepository<VendorCustomFieldEntity, Integer> {

    List<VendorCustomFieldEntity> findByVendorsId(Integer vendorsId);

    @Transactional
    @Modifying
    @Query("delete from VendorCustomFieldEntity v where v.customFieldsId = ?1")
    int deleteByCustomFieldsId(Integer customFieldsId);




}
