package com.internship.assetmanagement.dtos.asset;

import com.internship.assetmanagement.entities.TeamEntity;
import com.internship.assetmanagement.entities.UserEntity;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class AssetCreate implements Serializable {

    private String name;
    private String description;
    private String model;
    private String category;
    private String area;
    private LocalDate dateCreated;
    private UserEntity creatorUser;
    private BigInteger barcode;
    private LocalDate datePurchased;
    private BigDecimal price;
    private BigDecimal residualValue;
    private String usefulLifeUnit;
    private Integer usefulLifePeriod;
    private LocalDate datePlacedInService;
    private LocalDate dateWarrantyEnds;
    private String additionalInfo;
    private Boolean trackAndLogUsage;
    private String checkInProcedure;
    private String checkOutProcedure;
    private TeamEntity team;
    private UserEntity primaryUser;
    private UserEntity secondaryUser;
    private AssetCreate parentAsset;



}

