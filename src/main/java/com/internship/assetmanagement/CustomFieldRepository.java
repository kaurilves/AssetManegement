package com.internship.assetmanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomFieldRepository extends JpaRepository<CustomFieldEntity, Integer> {
}
