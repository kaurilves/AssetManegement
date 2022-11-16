package com.internship.assetmanagement.entities.asset;

import com.internship.assetmanagement.entities.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "activity_log")
public class ActivityLogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private AssetEntity assetEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "activity_type")
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    @Column(name = "comment")
    private String comment;

    @Column(name = "timestamp")
    @UpdateTimestamp
    private LocalDateTime dateTime;

    public ActivityLogEntity (AssetEntity assetEntity, UserEntity userEntity, ActivityType activityType){
        this.assetEntity = assetEntity;
        this.userEntity = userEntity;
        this.activityType = activityType;
        this.dateTime = LocalDateTime.now();
    }
}

