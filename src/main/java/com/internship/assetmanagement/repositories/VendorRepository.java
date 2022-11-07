package com.internship.assetmanagement.repositories;

import com.internship.assetmanagement.entities.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Integer> {
    @Query("""
            select v from VendorEntity v
            where upper(v.companyName) = upper(?1) or upper(v.address) = upper(?2) or upper(v.website) = 
            upper(?3) or upper(v.contactName) = upper(?4) or upper(v.email) = upper(?5) or upper(v.description) = 
            upper(?6) or upper(v.vendorType) = upper(?7) or upper(v.rate) = upper(?8)""")
    List<VendorEntity> findAllBySearchWord(String searchWord);

}
