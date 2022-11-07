package com.internship.assetmanagement;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class VendorCreate {

    @NotNull
    @NotBlank
    private String companyName;

    private String address;

    private String website;

    private String contactName;

    private String email;

    private String vendorType;

    private String description;

    @Positive
    private BigDecimal rate;

}
