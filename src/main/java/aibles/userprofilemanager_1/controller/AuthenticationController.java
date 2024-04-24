package aibles.userprofilemanager_1.controller;



import aibles.userprofilemanager_1.dto.LoginRequest;
import aibles.userprofilemanager_1.dto.TokenResponse;
import aibles.userprofilemanager_1.service.service.AuthTokenService;
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

    /**
     * Authenticate user and create an access token and a refresh token.
     *
     * @param loginRequest A DTO that includes the username and password.
     * @return a ResponseEntity containing the JWTs.
     */
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        // Authentication logic here (You would typically check username and password against a database)
        String userId = "SomeUserID";  // This should come from your user database after authentication
        String email = "user@example.com";  // Example email
        String username = loginRequest.getUsername();  // Assuming username is part of the login request
        String role = "USER";  // Example role

        String accessToken = authTokenService.generateAccessToken(userId, email, username, role);
        String refreshToken = authTokenService.generateRefreshToken(userId, email, username, role);

        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    }

    /**
     * Refresh the access token using a refresh token.
     *
     * @param refreshToken the refresh token used to generate a new access token
     * @return a ResponseEntity containing the new JWT.
     */
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshAccessToken(@RequestBody String refreshToken) {
        String userId = authTokenService.getSubjectFromRefreshToken(refreshToken);

        if (!authTokenService.validateRefreshToken(refreshToken, userId)) {
            return ResponseEntity.status(401).body(new TokenResponse("Invalid refresh token", null));
        }

        String email = "user@example.com";  // Example email, should be fetched based on userId
        String username = "username";       // Example username, should be fetched based on userId
        String role = "USER";               // Example role, should be fetched based on userId

        String newAccessToken = authTokenService.generateAccessToken(userId, email, username, role);

        return ResponseEntity.ok(new TokenResponse(newAccessToken, refreshToken));
    }
}

