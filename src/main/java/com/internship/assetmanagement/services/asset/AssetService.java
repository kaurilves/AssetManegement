package com.internship.assetmanagement.services.asset;

import com.internship.assetmanagement.dtos.asset.Asset;
import com.internship.assetmanagement.dtos.asset.AssetCreate;
import com.internship.assetmanagement.entities.asset.*;
import com.internship.assetmanagement.entities.user.UserEntity;
import com.internship.assetmanagement.mappers.UserMapper;
import com.internship.assetmanagement.mappers.asset.AssetMapper;
import com.internship.assetmanagement.mappers.LocationMapper;
import com.internship.assetmanagement.repositories.asset.*;
import com.internship.assetmanagement.repositories.other.*;
import com.internship.assetmanagement.repositories.vendor.VendorRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

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

    @Resource
    private VendorRepository vendorRepository;

    @Resource
    private CustomerRepository customerRepository;

    @Resource
    private AssetVendorRepository assetVendorRepository;

    @Resource
    private AssetCustomerRepository assetCustomerRepository;

    @Resource
    private UserMapper userMapper;

    @Resource
    private LocationMapper locationMapper;

    public Asset addAsset(UserEntity userEntity, AssetCreate assetCreate) throws Exception {
        AssetEntity assetEntity = assetMapper.assetCreateToAssetEntity(assetCreate);
        assetEntity.setCreatorUser(userEntity);
        assetEntity.setDateCreated(LocalDate.now());

        // adds location to asset
        if (assetCreate.getLocationId() != null) {
            if (locationRepository.findById(assetCreate.getLocationId()).isEmpty()) {
                throw new Exception("Location not found");
            } else {
                assetEntity.setLocation(locationRepository.findById(assetCreate.getLocationId()).get());
            }
        } else {
            assetEntity.setLocation(null);
        }
        // adds parent_asset to asset
        if (assetCreate.getParentAssetId() != null && assetCreate.getParentAssetId() > 0) {
            if (assetRepository.findById(assetCreate.getParentAssetId()).isEmpty()) {
                throw new Exception("Asset not found");
            } else {
                assetEntity.setParentAsset(assetRepository.findById(assetCreate.getParentAssetId()).get());
            }
        } else {
            assetEntity.setParentAsset(null);
        }

        // adds primary user to asset
        if (assetCreate.getPrimaryUserId() != null ) {
            if (userRepository.findById(assetCreate.getPrimaryUserId()).isEmpty()) {
                throw new Exception("User not found");
            }
            assetEntity.setPrimaryUser(userRepository.findById(assetCreate.getPrimaryUserId()).get());
        } else {
            assetEntity.setPrimaryUser(null);
        }
        assetRepository.save(assetEntity);

        // adds parts to asset
        if (assetCreate.getPartsIds() != null && !assetCreate.getPartsIds().isEmpty()) {
            AssetPartEntity assetPartEntity = new AssetPartEntity();
            for (Integer partId : assetCreate.getPartsIds()) {
                assetPartEntity.setAssetEntity(assetEntity);
                if (partRepository.findById(partId).isEmpty()) {
                    throw new Exception("Part not found!");
                }
                assetPartEntity.setPartEntity(partRepository.findById(partId).get());
                assetPartRepository.save(assetPartEntity);
            }
        }

        // add teams to asset
        if (assetCreate.getTeamsIds() != null && !assetCreate.getTeamsIds().isEmpty()) {
            AssetTeamEntity assetTeamEntity = new AssetTeamEntity();
            for (Integer teamId : assetCreate.getTeamsIds()) {
                assetTeamEntity.setAssetEntity(assetEntity);
                if (teamRepository.findById(teamId).isEmpty())
                    throw new Exception("Team not found!");
                assetTeamEntity.setTeamEntity(teamRepository.findById(teamId).get());
                assetTeamRepository.save(assetTeamEntity);
            }
        }

        // add secondary users to asset entity
        if (assetCreate.getSecondaryUsersIds() != null && !assetCreate.getSecondaryUsersIds().isEmpty()) {
            AssetUserEntity assetUserEntity = new AssetUserEntity();
            for (Integer secondaryUserId : assetCreate.getSecondaryUsersIds()) {
                assetUserEntity.setAssetEntity(assetEntity);

                assetUserEntity.setUserEntity(userRepository.findById(secondaryUserId).get());
                assetUserRepository.save(assetUserEntity);
            }
        }

        //If asset is created then automatically it is marked as Operational
        changeAssetOperationalStatus(1, userEntity, assetEntity.getId());


        //If asset usage is marked as trackable, then sets it checked in as creator user
        if (assetCreate.getTrackAndLogUsage()) {
            setAssetAsCheckedInOrCheckedOut(assetEntity.getId(), userEntity, null);
        }

        // adds list of vendors to asset
        if (assetCreate.getVendorIds() != null && !assetCreate.getVendorIds().isEmpty()) {
            for (Integer vendorId : assetCreate.getVendorIds()){
                AssetVendorEntity assetVendorEntity = new AssetVendorEntity();
                if(vendorRepository.findById(vendorId).isEmpty()){
                    throw new Exception("Vendor not found");
                }
                assetVendorEntity.setVendorEntity(vendorRepository.findById(vendorId).get());
                assetVendorEntity.setAssetEntity(assetEntity);
                assetVendorRepository.save(assetVendorEntity);
            }
        }

        // adds list of customers to asset
        if (assetCreate.getCustomerIds() != null && !assetCreate.getCustomerIds().isEmpty()) {
            for (Integer customerId : assetCreate.getCustomerIds()){
                AssetCustomerEntity assetCustomerEntity = new AssetCustomerEntity();
                if(customerRepository.findById(customerId).isEmpty()){
                    throw new Exception("Customer not found");
                }
                assetCustomerEntity.setCustomerEntity(customerRepository.findById(customerId).get());
                assetCustomerEntity.setAssetEntity(assetEntity);
                assetCustomerRepository.save(assetCustomerEntity);
            }
        }

        Asset asset = assetMapper.assetEntityToAsset(assetEntity);
        asset.setCreatorUser(userMapper.userEntityToUser(assetEntity.getCreatorUser()));
        asset.setPrimaryUser(userMapper.userEntityToUser(assetEntity.getPrimaryUser()));
        asset.setParentAsset(getAsset(assetEntity.getParentAsset().getId()));
        asset.setLocation(locationMapper.locationEntityToLocation(assetEntity.getLocation()));

        return asset;
    }

    public void changeAssetOperationalStatus(Integer operationalStatusId, UserEntity userEntity, Integer assetId) throws Exception {
        if (assetRepository.findById(assetId).isEmpty()) {
            throw new Exception("Asset not found");
        }
        AssetEntity assetEntity = assetRepository.findById(assetId).get();
        ActivityLogEntity activityLogEntity = new ActivityLogEntity(assetEntity, userEntity, ActivityType.RELIABILITY);
        activityLogRepository.save(activityLogEntity);

        ReliabilityLogEntity reliabilityLogEntity = new ReliabilityLogEntity();
        if (operationalStatusRepository.findById(operationalStatusId).isEmpty()) {
            throw new Exception("Operational status not found");
        }
        reliabilityLogEntity.setOperationalStatusEntity(operationalStatusRepository.findById(operationalStatusId).get());
        reliabilityLogEntity.setActivityLogEntity(activityLogEntity);
        reliabilityLogRepository.save(reliabilityLogEntity);
    }

    public void setAssetAsCheckedInOrCheckedOut(Integer assetId, UserEntity userEntity, String comment) throws Exception {
        if (assetRepository.findById(assetId).isEmpty()) {
            throw new Exception("Asset not found");
        }
        AssetEntity assetEntity = assetRepository.findById(assetId).get();
        ActivityLogEntity activityLogEntity = new ActivityLogEntity(assetEntity, userEntity, ActivityType.USAGE_LOG);
        activityLogRepository.save(activityLogEntity);

        AssetUsageLogEntity assetUsageLogEntity = new AssetUsageLogEntity();
        assetUsageLogEntity.setActivityLogEntity(activityLogEntity);
        assetUsageLogEntity.setComment(comment);
        assetUsageLogEntity.setIsCheckedIn(!assetUsageLogEntity.getIsCheckedIn());
        assetUsageLogRepository.save(assetUsageLogEntity);
    }

     public Asset getAsset(Integer assetId) throws Exception {
        if(assetRepository.findById(assetId).isEmpty()){
            throw new Exception("Asset not found");
         }
         AssetEntity assetEntity = assetRepository.findById(assetId).get();
         Asset asset = assetMapper.assetEntityToAsset(assetEntity);
         asset.setCreatorUser(userMapper.userEntityToUser(assetEntity.getCreatorUser()));
         asset.setPrimaryUser(userMapper.userEntityToUser(assetEntity.getPrimaryUser()));
         asset.setParentAsset(getAsset(assetEntity.getParentAsset().getId()));
         asset.setLocation(locationMapper.locationEntityToLocation(assetEntity.getLocation()));
        return asset;
     }


}
