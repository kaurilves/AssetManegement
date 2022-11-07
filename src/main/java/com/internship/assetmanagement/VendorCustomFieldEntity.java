package com.internship.assetmanagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "vendor_custom_fields")
public class VendorCustomFieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "vendors_id", nullable = false)
    private Integer vendorsId;

    @Column(name = "custom_fields_id", nullable = false)
    private Integer customFieldsId;

}
