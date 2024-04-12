package aibles.userprofilemanager_1.controller;


import aibles.userprofilemanager_1.dto.UserProfileResponse;
import aibles.userprofilemanager_1.service.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userProfiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }
    // Lấy danh sách tất cả UserProfile
    @GetMapping
    public List<UserProfileResponse> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }
}