package com.internship.assetmanagement.entities.vendor;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assets")
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "model")
    private String model;

    @Column(name = "category")
    private String category;

    @Column(name = "area")
    private String area;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "creator_user_id")
    private UserEntity creatorUser;

    @Column(name = "date_purchased")
    private LocalDate datePurchased;

    @Column(name = "barcode")
    private BigInteger barcode;

    @Column(name = "purchase_price")
    private BigDecimal price;

    @Column(name = "residual_value")
    private BigDecimal residualValue;

    @Column(name = "useful_life_unit")
    private String usefulLifeUnit;

    @Column(name = "useful_life_quantity")
    private Integer usefulLifeQuantity;

    @Column(name = "date_placed_in_service")
    private LocalDate datePlacedInService;

    @Column(name = "date_warranty_ends")
    private LocalDate dateWarrantyEnds;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "check_in_procedure")
    private String checkInProcedure;

    @Column(name = "check_out_procedure")
    private String checkOutProcedure;

    @Column(name = "teams_id")
    private TeamEntity team;

    @Column(name = "primary_user_id")
    private UserEntity primaryUser;

    @Column(name = "secondary_user_id")
    private UserEntity secondaryUser;

    @Column(name = "parent_asset_id")
    private AssetEntity parentAsset;



}

