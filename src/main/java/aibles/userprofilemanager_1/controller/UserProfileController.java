package aibles.userprofilemanager_1.controller;

import aibles.userprofilemanager_1.dto.request.UserProfileRequest;
import aibles.userprofilemanager_1.dto.UserProfileResponse;
import aibles.userprofilemanager_1.service.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userprofiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity<UserProfileResponse> createUserProfile(@RequestBody UserProfileRequest request) {
        UserProfileResponse response = userProfileService.createUserProfile(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileResponse> updateUserProfile(@PathVariable Long id, @RequestBody UserProfileRequest request) {
        UserProfileResponse response = userProfileService.updateUserProfile(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfileById(@PathVariable Long id) {
        UserProfileResponse response = userProfileService.getUserProfileById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserProfileResponse>> getAllUserProfiles() {
        List<UserProfileResponse> responses = userProfileService.getAllUserProfiles();
        return ResponseEntity.ok(responses);
    }
}
