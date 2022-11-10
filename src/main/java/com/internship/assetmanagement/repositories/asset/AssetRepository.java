package com.internship.assetmanagement.repositories.asset;

import com.internship.assetmanagement.entities.asset.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssetRepository extends JpaRepository<AssetEntity, Integer> {

}
