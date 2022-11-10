package com.internship.assetmanagement.dtos.asset;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class AssetInList implements Serializable {

    private Integer id;  // +
    private String description; //+
    private String location; //+
    private String model; //+
    private String category; //+
    private String area; //+
    private String status; //+
    private BigInteger barcode; //+
    private LocalDate datePurchased; //+
    private BigDecimal price; //+
    private BigDecimal residualValue; //+
    private BigDecimal currentValue; //+
    private String usefulLifePeriod; //+
    private LocalDate datePlacedInService; //+
    private LocalDate dateWarrantyEnds; //+
    private BigDecimal uptimePercentage; //+
    private LocalDate lastCheckIn; //+
    private String isCheckedIn; //+
    private Integer numberOfCheckOuts; //+
    private Integer numberOfVendors; //+
    private Integer numberOfTeams; //+
    private String primaryUser; //+
    private Integer numberOfSecondaryUsers; //+
    private Integer numberOfOpenWorkOrders; //+
    private String parentAsset; //+



}

