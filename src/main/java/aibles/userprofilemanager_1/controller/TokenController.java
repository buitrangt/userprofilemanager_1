package aibles.userprofilemanager_1.controller;



import aibles.userprofilemanager_1.service.service.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private AuthTokenService authTokenService;

    @GetMapping("/validate/access/{token}")
    public ResponseEntity<String> validateAccessToken(@PathVariable String token) {
        String userId = authTokenService.getUserIdFromToken(token);
        boolean isValid = authTokenService.validateAccessToken(token, userId);
        return ResponseEntity.ok("Access Token is valid for user ID " + userId + ": " + isValid);
    }

    @GetMapping("/validate/refresh/{token}")
    public ResponseEntity<String> validateRefreshToken(@PathVariable String token) {
        String userId = authTokenService.getUserIdFromToken(token);
        boolean isValid = authTokenService.validateRefreshToken(token, userId);
        return ResponseEntity.ok("Refresh Token is valid for user ID " + userId + ": " + isValid);
    }
}

