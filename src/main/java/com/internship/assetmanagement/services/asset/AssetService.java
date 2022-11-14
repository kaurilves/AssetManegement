package com.internship.assetmanagement.services.asset;

import com.internship.assetmanagement.dtos.asset.AssetResponse;
import com.internship.assetmanagement.dtos.asset.AssetCreate;
import com.internship.assetmanagement.entities.asset.*;
import com.internship.assetmanagement.entities.others.UserEntity;
import com.internship.assetmanagement.mappers.asset.AssetMapper;
import com.internship.assetmanagement.repositories.asset.*;
import com.internship.assetmanagement.repositories.other.LocationRepository;
import com.internship.assetmanagement.repositories.other.PartRepository;
import com.internship.assetmanagement.repositories.other.TeamRepository;
import com.internship.assetmanagement.repositories.other.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AssetService {

    @Resource
    private AssetMapper assetMapper;

    @Resource
    private AssetRepository assetRepository;

    @Resource
    private LocationRepository locationRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private PartRepository partRepository;

    @Resource
    private TeamRepository teamRepository;

    @Resource
    private AssetPartRepository assetPartRepository;

    @Resource
    private AssetTeamRepository assetTeamRepository;

    @Resource
    private AssetUserRepository assetUserRepository;

    @Resource
    private OperationalStatusRepository operationalStatusRepository;

    @Resource
    private ReliabilityLogRepository reliabilityLogRepository;

    @Resource
    private AssetUsageLogRepository assetUsageLogRepository;

    @Resource
    private ActivityLogRepository activityLogRepository;


    public AssetResponse addAsset(AssetCreate assetCreate) throws Exception {
        AssetEntity assetEntity = assetMapper.assetCreateToAssetEntity(assetCreate);

        assetEntity.setDateCreated(LocalDate.now());

        // adds location to asset
        if (assetCreate.getLocationId() != null) {
            if (locationRepository.findById(assetCreate.getLocationId()).isEmpty()) {
                throw new Exception("bla bla bla");
            } assetEntity.setLocation(locationRepository.findById(assetCreate.getLocationId()).get());
        }
        // adds primary user to asset
        if (assetCreate.getPrimaryUserId() != null) {
            if (assetRepository.findById(assetCreate.getPrimaryUserId()).isEmpty()) {
                throw new Exception("bla bla bla");
            }
            assetEntity.setPrimaryUser(userRepository.findById(assetCreate.getPrimaryUserId()).get());
        }
        assetRepository.save(assetEntity);

        // adds parts to asset
        if (assetCreate.getPartsIds() != null && !assetCreate.getPartsIds().isEmpty()) {
            AssetPartEntity assetPartEntity = new AssetPartEntity();
            for (Integer partId : assetCreate.getPartsIds()) {
                assetPartEntity.setAssetEntity(assetEntity);
                assetPartEntity.setPartEntity(partRepository.findById(partId).get());
                assetPartRepository.save(assetPartEntity);
            }
        }

        // add teams to asset
        if (assetCreate.getTeamsIds() != null && !assetCreate.getTeamsIds().isEmpty()) {
            AssetTeamEntity assetTeamEntity = new AssetTeamEntity();
            for (Integer teamId : assetCreate.getTeamsIds()) {
                assetTeamEntity.setAssetEntity(assetEntity);
                assetTeamEntity.setTeamEntity(teamRepository.findById(teamId).get());
                assetTeamRepository.save(assetTeamEntity);
            }
        }

        // add secondary users to asset entity
        if (assetCreate.getSecondaryUsersIds() != null || assetCreate.getSecondaryUsersIds().size() > 0) {
            AssetUserEntity assetUserEntity = new AssetUserEntity();
            for (Integer secondaryUserId : assetCreate.getSecondaryUsersIds()) {
                assetUserEntity.setAssetEntity(assetEntity);
                assetUserEntity.setUserEntity(userRepository.findById(secondaryUserId).get());
                assetUserRepository.save(assetUserEntity);
            }
        }


        //if asset usage is trackable, then set checked in as creator user .
        if (assetCreate.getTrackAndLogUsage()) {
            setAssetAsCheckedInOrCheckedOut(assetEntity.getId(), assetCreate.getCreatorUserId(), null);
        }
        return assetMapper.assetEntityToAssetResponse(assetEntity);
    }

    public void changeAssetOperationalStatus(Integer operationalStatusId, Integer assetId, Integer userId) {
        UserEntity userEntity = userRepository.getById(userId);
        AssetEntity assetEntity = assetRepository.getById(assetId);
        ActivityLogEntity activityLogEntity = new ActivityLogEntity(assetEntity, userEntity, ActivityType.RELIABILITY);
        activityLogEntity = activityLogRepository.save(activityLogEntity);

        ReliabilityLogEntity reliabilityLogEntity = new ReliabilityLogEntity();
        reliabilityLogEntity.setOperationalStatusEntity(operationalStatusRepository.findById(operationalStatusId).get());
        reliabilityLogEntity.setActivityLogEntity(activityLogEntity);
        reliabilityLogRepository.save(reliabilityLogEntity);
    }

    public void setAssetAsCheckedInOrCheckedOut(Integer assetId, Integer userId, String comment) throws Exception {
        UserEntity userEntity = userRepository.getById(userId);
        AssetEntity assetEntity = assetRepository.getById(assetId);
        ActivityLogEntity activityLogEntity = new ActivityLogEntity(assetEntity, userEntity, ActivityType.USAGE_LOG);
        activityLogEntity = activityLogRepository.save(activityLogEntity);

        AssetUsageLogEntity assetUsageLogEntity = new AssetUsageLogEntity();
        assetUsageLogEntity.setActivityLogEntity(activityLogEntity);
        assetUsageLogEntity.setComment(comment);
        assetUsageLogEntity.setIsCheckedIn(!assetUsageLogEntity.getIsCheckedIn());
        assetUsageLogRepository.save(assetUsageLogEntity);

    }
}
