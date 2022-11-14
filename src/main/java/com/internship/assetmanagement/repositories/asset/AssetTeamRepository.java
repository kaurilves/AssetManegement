package com.internship.assetmanagement.repositories.asset;

import com.internship.assetmanagement.entities.asset.AssetTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssetTeamRepository extends JpaRepository<AssetTeamEntity, Integer> {

}
