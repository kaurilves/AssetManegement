package com.internship.assetmanagement.repositories.asset;

import com.internship.assetmanagement.entities.asset.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AssetRepository extends JpaRepository<AssetEntity, Integer> {


    List<AssetEntity> findAllByUserCompanyId(Integer userCompanyId);
}
