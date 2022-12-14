package com.internship.assetmanagement.entities.vendor;

import com.internship.assetmanagement.entities.others.CompanyEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vendors")
public class VendorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_company_id", nullable = false)
    private CompanyEntity userCompany;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "website")
    private String website;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "email")
    private String email;

    @Column(name = "vendor_type")
    private String vendorType;

    @Column(name = "description")
    private String description;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "date_created")
    private LocalDate dateCreated;

}




