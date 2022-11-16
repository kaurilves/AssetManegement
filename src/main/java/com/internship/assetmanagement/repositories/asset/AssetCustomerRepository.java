package com.internship.assetmanagement.repositories.asset;

import com.internship.assetmanagement.entities.asset.AssetCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetCustomerRepository extends JpaRepository<AssetCustomerEntity, Integer> {
}
