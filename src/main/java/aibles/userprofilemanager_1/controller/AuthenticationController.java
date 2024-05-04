package aibles.userprofilemanager_1.controller;



import aibles.userprofilemanager_1.dto.UserProfileResponse;
import aibles.userprofilemanager_1.dto.request.LoginRequest;
import aibles.userprofilemanager_1.dto.response.TokenResponse;
import aibles.userprofilemanager_1.service.service.AuthTokenService;
import aibles.userprofilemanager_1.service.service.ForgotPasswordService;
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
    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();


        UserProfileResponse userProfile = userProfileService.getUserProfileByUsername(username);
        String userId = userProfile.getId();
        String email = userProfile.getEmail();
        String role = userProfile.getRole();


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


        UserProfileResponse userProfile = userProfileService.getUserProfileById(Long.parseLong(userId));
        String email = userProfile.getEmail();
        String username = userProfile.getUsername();
        String role = userProfile.getRole();


        String newAccessToken = authTokenService.generateAccessToken(userId, email, username, role);

        return ResponseEntity.ok(new TokenResponse(newAccessToken, refreshToken));
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<String> forgotPassword(@RequestBody String email) {
        forgotPasswordService.forgotPassword(email);
        return ResponseEntity.ok("Reset password email sent successfully");
    }


}


