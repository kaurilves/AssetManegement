package com.internship.assetmanagement.repositories;

import com.internship.assetmanagement.entities.others.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

}
