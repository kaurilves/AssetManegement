package com.internship.assetmanagement.entities.asset;

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
@Table(name = "asset_usage_log")
public class AssetUsageLogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_log", nullable = false)
    private ActivityLogEntity activityLogEntity;

    @Column(name = "is_checked_in")
    private Boolean isCheckedIn;

    @Column(name = "comment")
    private String comment;

}

