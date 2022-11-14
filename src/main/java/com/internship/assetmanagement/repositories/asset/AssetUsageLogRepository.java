package com.internship.assetmanagement.repositories.asset;

import com.internship.assetmanagement.entities.asset.AssetUsageLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssetUsageLogRepository extends JpaRepository<AssetUsageLogEntity, Integer> {

}
