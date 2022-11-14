package com.internship.assetmanagement.repositories.other;

import com.internship.assetmanagement.entities.others.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
