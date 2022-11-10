package com.internship.assetmanagement.repositories.vendor;

import com.internship.assetmanagement.entities.vendor.CustomFieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomFieldRepository extends JpaRepository<CustomFieldEntity, Integer> {
}
