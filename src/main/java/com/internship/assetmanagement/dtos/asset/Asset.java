package com.internship.assetmanagement.dtos.asset;

import com.internship.assetmanagement.dtos.others.Team;
import lombok.Data;
import org.apache.tomcat.jni.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Data
public class Asset implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private String model;
    private String category;
    private String area;
    private LocalDate dateCreated;
    private User creatorUser;
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
    private List<Team> teams;
    private String primaryUserName;
    private List<String> secondaryUsers;
    private Asset parentAsset;



}

