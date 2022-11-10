package com.internship.assetmanagement.entities.vendor;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vendor_custom_field")
public class VendorCustomFieldEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "vendor_id", nullable = false)
    private Integer vendorsId;

    @Column(name = "custom_field_id", nullable = false)
    private Integer customFieldsId;

}
