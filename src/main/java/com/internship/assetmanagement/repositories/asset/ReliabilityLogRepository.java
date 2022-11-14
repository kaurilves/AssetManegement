package com.internship.assetmanagement.repositories.asset;

import com.internship.assetmanagement.entities.asset.ReliabilityLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReliabilityLogRepository extends JpaRepository<ReliabilityLogEntity, Integer> {

}
