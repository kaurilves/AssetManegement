package com.internship.assetmanagement.repositories.asset;

import com.internship.assetmanagement.entities.asset.CheckInCheckOutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CheckInCheckOutRepository extends JpaRepository<CheckInCheckOutEntity, Integer> {

}
