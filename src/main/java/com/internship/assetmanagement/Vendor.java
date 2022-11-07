package com.internship.assetmanagement;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class Vendor {

    private Integer id;
    private String companyName;
    private String address;
    private String website;
    private String contactName;
    private String email;
    private String vendorType;
    private String description;
    private BigDecimal rate;
    private LocalDate dateCreated;
    private List<CustomField> customFields;

}
