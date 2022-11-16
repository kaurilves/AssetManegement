package com.internship.assetmanagement.entities.asset;

import com.internship.assetmanagement.entities.others.CompanyEntity;
import com.internship.assetmanagement.entities.others.LocationEntity;
import com.internship.assetmanagement.entities.user.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "asset")
public class AssetEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_company_id", nullable = false)
    private CompanyEntity userCompany;

    @Column(name = "description")
    private String description;

    @Column(name = "model")
    private String model;

    @Column(name = "category")
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @Column(name = "area")
    private String area;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_user_id", nullable = false)
    private UserEntity creatorUser;

    @Column(name = "date_purchased")
    private LocalDate datePurchased;

    @Column(name = "barcode")
    private BigInteger barcode;

    @Column(name = "purchase_price")
    private BigDecimal price;

    @Column(name = "residual_value")
    private BigDecimal residualValue;

    @Column(name = "useful_life_quantity")
    private Integer usefulLifeQuantityInDays;

    @Column(name = "date_placed_in_service")
    private LocalDate datePlacedInService;

    @Column(name = "date_warranty_ends")
    private LocalDate dateWarrantyEnds;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "track_and_log_usage")
    private Boolean trackAndLogUsage;

    @Column(name = "check_in_procedure")
    private String checkInProcedure;

    @Column(name = "check_out_procedure")
    private String checkOutProcedure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_user_id")
    private UserEntity primaryUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_asset_id")
    private AssetEntity parentAsset;


}

