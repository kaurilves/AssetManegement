package com.internship.assetmanagement.entities.vendor;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vendor_custom_fields")
public class VendorCustomFieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "vendors_id", nullable = false)
    private Integer vendorsId;

    @Column(name = "custom_fields_id", nullable = false)
    private Integer customFieldsId;

}
