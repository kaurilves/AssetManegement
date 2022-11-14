package com.internship.assetmanagement.repositories.other;

import com.internship.assetmanagement.entities.others.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

}
