package com.internship.assetmanagement.entities.asset;

import com.internship.assetmanagement.entities.asset.AssetEntity;
import com.internship.assetmanagement.entities.asset.OperationalStatusEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reliability_log")
public class ReliabilityLogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_log_id", nullable = false)
    private ActivityLogEntity activityLogEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operational_status_id", nullable = false)
    private OperationalStatusEntity operationalStatusEntity;
}

