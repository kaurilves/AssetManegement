package com.internship.assetmanagement.repositories;

import com.internship.assetmanagement.entities.CustomFieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomFieldRepository extends JpaRepository<CustomFieldEntity, Integer> {
}
