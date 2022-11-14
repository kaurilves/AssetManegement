package com.internship.assetmanagement.repositories.asset;

import com.internship.assetmanagement.entities.asset.AssetUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssetUserRepository extends JpaRepository<AssetUserEntity, Integer> {

}
