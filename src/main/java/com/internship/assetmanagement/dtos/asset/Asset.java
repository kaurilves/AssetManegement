package com.internship.assetmanagement.dtos.asset;

import com.internship.assetmanagement.dtos.others.Team;
import lombok.Data;
import org.apache.tomcat.jni.User;

import javax.servlet.http.Part;
import javax.xml.stream.Location;
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
    private Location location;
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
    private User primaryUser;
    private List<Team> teams;
    private List<User> secondaryUsers;
    private List<Part> parts;
    private Asset parentAsset;



}

