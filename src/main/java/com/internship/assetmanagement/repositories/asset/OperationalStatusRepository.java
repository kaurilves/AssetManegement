package com.internship.assetmanagement.repositories.asset;

import com.internship.assetmanagement.entities.asset.OperationalStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OperationalStatusRepository extends JpaRepository<OperationalStatusEntity, Integer> {

}
