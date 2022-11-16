package com.internship.assetmanagement.repositories.asset;

import com.internship.assetmanagement.entities.asset.AssetVendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetVendorRepository extends JpaRepository<AssetVendorEntity, Integer> {
}
