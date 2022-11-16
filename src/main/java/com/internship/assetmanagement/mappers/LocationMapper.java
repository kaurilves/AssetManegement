package com.internship.assetmanagement.mappers;

import com.internship.assetmanagement.dtos.others.Location;
import com.internship.assetmanagement.entities.others.LocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface LocationMapper {

    Location locationEntityToLocation(LocationEntity location);
}
