package com.internship.assetmanagement.dtos.asset;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Data
public class AssetCreate implements Serializable {

    private String name;
    private Integer userCompanyId;
    private String description;
    private String model;
    private String category;
    private Integer locationId;
    private String area;
    private LocalDate dateCreated;
    private Integer creatorUserId;
    private BigInteger barcode;
    private LocalDate datePurchased;
    private BigDecimal price;
    private BigDecimal residualValue;
    private String usefulLifeUnit;
    private Integer usefulLifeQuantity;
    private LocalDate datePlacedInService;
    private LocalDate dateWarrantyEnds;
    private String additionalInfo;
    private Boolean trackAndLogUsage;
    private String checkInProcedure;
    private String checkOutProcedure;
    private Integer primaryUserId;
    private List<Integer> secondaryUsersIds;
    private List<Integer> teamsIds;
    private List<Integer> partsIds;
    private Integer parentAssetId;

}

