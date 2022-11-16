package com.internship.assetmanagement.repositories.other;

import com.internship.assetmanagement.entities.others.CustomerEntity;
import com.internship.assetmanagement.entities.vendor.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {


}
