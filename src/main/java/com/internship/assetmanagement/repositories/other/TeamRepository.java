package com.internship.assetmanagement.repositories.other;

import com.internship.assetmanagement.entities.others.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {

}
