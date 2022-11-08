package com.internship.assetmanagement.dtos;


import lombok.Data;


import java.math.BigDecimal;

@Data
public class VendorCreate {


    private String companyName;

    private String address;

    private String website;

    private String contactName;

    private String email;

    private String vendorType;

    private String description;

    private BigDecimal rate;

}
