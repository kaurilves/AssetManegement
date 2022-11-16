package com.internship.assetmanagement.dtos.asset;

import com.internship.assetmanagement.dtos.others.Location;
import lombok.Data;
import org.apache.tomcat.jni.User;

import javax.servlet.http.Part;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Asset implements Serializable {

    private Integer id;
    private String name; //+
    private String description; //+
    private String model; //+
    private String category; //+
    private Location location; //+
    private String area; //+
    private LocalDate dateCreated; //+
    private User creatorUser;
    private BigInteger barcode; //+
    private LocalDate datePurchased; //+
    private LocalDate datePlacedInService; //+
    private BigDecimal price; //+
    private BigDecimal residualValue; //+
    private String usefulLifeUnit; //+
    private Integer usefulLifePeriod; //+
    private LocalDate dateWarrantyEnds; //+
    private String additionalInfo;
    private String checkInProcedure;
    private String checkOutProcedure;
    private User primaryUser; //+
    private Asset parentAsset; //+

    private Integer numberOfOpenWorkOrders; //+
    private List<String> teams; //+
    private List<String> secondaryUsers; //+
    private List<Part> parts;
    private List<String> vendors; //+
    private List<String> customers;
    private String checkedInOrOut; //+
    private String operationalStatus; //+
    private LocalDateTime lastTimeCheckedIn; //+
    private Integer numberOfCheckOuts; //+
    private Integer uptimePercentage; //+



}

