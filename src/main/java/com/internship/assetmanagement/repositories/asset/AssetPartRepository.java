package com.internship.assetmanagement.repositories.asset;

import com.internship.assetmanagement.entities.others.AssetPartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssetPartRepository extends JpaRepository<AssetPartEntity, Integer> {

}
