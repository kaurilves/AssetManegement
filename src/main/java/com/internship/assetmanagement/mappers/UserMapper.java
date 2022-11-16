package com.internship.assetmanagement.mappers;

import com.internship.assetmanagement.dtos.user.UserCreate;
import com.internship.assetmanagement.entities.user.UserEntity;
import org.apache.tomcat.jni.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
     UserEntity userCreateToUserEntity(UserCreate userCreate);

    User userEntityToUser(UserEntity userEntity);
}
