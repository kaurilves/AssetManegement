package com.internship.assetmanagement.repositories.other;

import com.internship.assetmanagement.entities.others.PartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PartRepository extends JpaRepository<PartEntity, Integer> {

}
