package com.internship.assetmanagement.repositories.asset;

import com.internship.assetmanagement.entities.others.AssetPartEntity;
import com.internship.assetmanagement.entities.others.AssetTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssetTeamRepository extends JpaRepository<AssetTeamEntity, Integer> {

}
