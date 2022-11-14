package com.internship.assetmanagement.services.asset;

import com.internship.assetmanagement.dtos.asset.Asset;
import com.internship.assetmanagement.dtos.asset.AssetCreate;
import com.internship.assetmanagement.entities.asset.*;
import com.internship.assetmanagement.mappers.asset.AssetMapper;
import com.internship.assetmanagement.repositories.*;
import com.internship.assetmanagement.repositories.asset.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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


    public Asset addAsset(AssetCreate assetCreate) {
        AssetEntity assetEntity = assetMapper.assetCreateToAssetEntity(assetCreate);

        // adds location to asset
        if (assetCreate.getLocationId() != null){
            assetEntity.setLocation(locationRepository.findById(assetCreate.getLocationId()).get());
        } else {
            assetEntity.setLocation(null);
        }

        // adds primary user to asset
        if (assetCreate.getPrimaryUserId() != null) {
            assetEntity.setPrimaryUser(userRepository.findById(assetCreate.getPrimaryUserId()).get());
        } else {
            assetEntity.setParentAsset(null);
        }
        assetRepository.save(assetEntity);

        // adds parts to asset
        if (assetCreate.getPartsIds() != null || assetCreate.getPartsIds().size() > 0) {
            AssetPartEntity assetPartEntity = new AssetPartEntity();
            for (Integer partId : assetCreate.getPartsIds()){
                assetPartEntity.setAssetEntity(assetEntity);
                assetPartEntity.setPartEntity(partRepository.findById(partId).get());
                assetPartRepository.save(assetPartEntity);
            }
        }

        // add teams to asset
        if (assetCreate.getTeamsIds() != null || assetCreate.getTeamsIds().size() > 0) {
            AssetTeamEntity assetTeamEntity = new AssetTeamEntity();
            for (Integer teamId : assetCreate.getTeamsIds()){
                assetTeamEntity.setAssetEntity(assetEntity);
                assetTeamEntity.setTeamEntity(teamRepository.findById(teamId).get());
                assetTeamRepository.save(assetTeamEntity);
            }
        }

        // add secondary users to asset entity
        if (assetCreate.getSecondaryUsersIds() != null || assetCreate.getSecondaryUsersIds().size() > 0) {
            AssetUserEntity assetUserEntity = new AssetUserEntity();
            for (Integer secondaryUserId : assetCreate.getSecondaryUsersIds()){
                assetUserEntity.setAssetEntity(assetEntity);
                assetUserEntity.setUserEntity(userRepository.findById(secondaryUserId).get());
                assetUserRepository.save(assetUserEntity);
            }
        }

        // set asset as operational status to "Operational"
        changeAssetOperationalStatus(1, assetEntity.getId());

        //if asset is assigned to primary user, then set status as checked in.
        if (assetCreate.getPrimaryUserId() != null){
            setAssetAsCheckedInOrCheckedOut(assetEntity.getId(), assetCreate.getPrimaryUserId(), "Checked in", null);
        }


        return assetMapper.assetEntityToAsset(assetEntity);
    }

    public void changeAssetOperationalStatus (Integer operationalStatusId, Integer assetId) {
        ReliabilityLogEntity reliabilityLogEntity = new ReliabilityLogEntity();
        reliabilityLogEntity.setOperationalStatusEntity(operationalStatusRepository.findById(operationalStatusId).get());
        reliabilityLogEntity.setTimeStamp(LocalDateTime.now());
        reliabilityLogEntity.setAssetEntity(assetRepository.findById(assetId).get());
        reliabilityLogRepository.save(reliabilityLogEntity);
    }

    public void setAssetAsCheckedInOrCheckedOut(Integer assetId, Integer userId, String status, String comment){
        CheckInCheckOutEntity checkInCheckOutEntity = new CheckInCheckOutEntity();
        checkInCheckOutEntity.setAssetEntity(assetRepository.findById(assetId).get());
        checkInCheckOutEntity.setUserEntity(userRepository.findById(userId).get());
        checkInCheckOutEntity.setDateTime(LocalDateTime.now());
        checkInCheckOutEntity.setComment(comment);
        checkInCheckOutEntity.setStatus(status);
    }



}
