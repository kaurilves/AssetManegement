package com.internship.assetmanagement.repositories.asset;

import com.internship.assetmanagement.entities.asset.ActivityLogEntity;
import com.internship.assetmanagement.entities.asset.AssetPartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLogEntity, Integer> {

}
