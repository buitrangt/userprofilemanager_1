package aibles.userprofilemanager_1.service.service;

import aibles.userprofilemanager_1.dto.UserProfileRequest;
import aibles.userprofilemanager_1.dto.UserProfileResponse;


import java.util.List;

public interface UserProfileService {
    UserProfileResponse createUserProfile(UserProfileRequest request);
    UserProfileResponse updateUserProfile(Long id, UserProfileRequest request);
    void deleteUserProfile(Long id);
    UserProfileResponse getUserProfileById(Long id);
    List<UserProfileResponse> getAllUserProfiles();
}
