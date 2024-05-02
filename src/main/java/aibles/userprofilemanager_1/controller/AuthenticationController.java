package aibles.userprofilemanager_1.controller;



import aibles.userprofilemanager_1.dto.UserProfileResponse;
import aibles.userprofilemanager_1.dto.request.LoginRequest;
import aibles.userprofilemanager_1.dto.response.TokenResponse;
import aibles.userprofilemanager_1.service.service.AuthTokenService;
import aibles.userprofilemanager_1.service.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthTokenService authTokenService;
    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Authenticate user with username and password (e.g., validate credentials against database)
        // Assuming the user is authenticated successfully and retrieve user profile
        UserProfileResponse userProfile = userProfileService.getUserProfileByUsername(username);
        String userId = userProfile.getId();
        String email = userProfile.getEmail();
        String role = userProfile.getRole();

        // Generate access token and refresh token
        String accessToken = authTokenService.generateAccessToken(userId, email, username, role);
        String refreshToken = authTokenService.generateRefreshToken(userId, email, username, role);

        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    }
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshAccessToken(@RequestBody String refreshToken) {
        String userId = authTokenService.getSubjectFromRefreshToken(refreshToken);

        if (!authTokenService.validateRefreshToken(refreshToken, userId)) {
            return ResponseEntity.status(401).body(new TokenResponse("Invalid refresh token", null));
        }

        // Get user profile information based on userId from the database
        UserProfileResponse userProfile = userProfileService.getUserProfileById(Long.parseLong(userId));
        String email = userProfile.getEmail();
        String username = userProfile.getUsername();
        String role = userProfile.getRole();

        // Generate a new access token
        String newAccessToken = authTokenService.generateAccessToken(userId, email, username, role);

        return ResponseEntity.ok(new TokenResponse(newAccessToken, refreshToken));
    }

}

