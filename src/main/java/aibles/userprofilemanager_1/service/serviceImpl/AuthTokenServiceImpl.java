package aibles.userprofilemanager_1.service.serviceImpl;

import aibles.userprofilemanager_1.service.service.AuthTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class AuthTokenServiceImpl implements AuthTokenService {

    @Value("LS0tLS1CRUdJTiBQVUJMSUMgS0VZLS0tLS0KTUlJQklqQU5CZ2txaGtpRzl3MEJBUUVGQUFPQ0FROEFNSUlCQ2dLQ0FRRUF4c3R5NEpFNnlqaUJTTE54RFViZQpRUEhHdDhDbTlWeXBZamxiSjh3Y0dGZkdQTGRjcDEvS2xxSjZWVXpHWmoveGZEWUQwamRRdTNzTzhTTjY2eGhVCitielAzNEUxRklNNkRMV1hYdUdqdVB6MFVQOVc0dnNvNThQM0xkTFpXSXU1")
    private String SECRET_KEY;

    @Value("${app.jwt.access.token.expiry}")
    private long ACCESS_TOKEN_EXPIRY;

    @Value("${app.jwt.refresh.token.expiry}")
    private long REFRESH_TOKEN_EXPIRY;

    @Override
    public String generateAccessToken(String userId, String email, String username, String role) {
        return createToken(userId, email, username, role, ACCESS_TOKEN_EXPIRY);
    }

    @Override
    public String getSubjectFromAccessToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    @Override
    public boolean validateAccessToken(String token, String userId) {
        final String subject = getSubjectFromAccessToken(token);
        return (userId.equals(subject) && !isTokenExpired(token));
    }

    @Override
    public String generateRefreshToken(String userId, String email, String username, String role) {
        return createToken(userId, email, username, role, REFRESH_TOKEN_EXPIRY);
    }

    @Override
    public String getSubjectFromRefreshToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    @Override
    public boolean validateRefreshToken(String token, String userId) {
        final String subject = getSubjectFromRefreshToken(token);
        return (userId.equals(subject) && !isTokenExpired(token));
    }

    private String createToken(String userId, String email, String username, String role, long validity) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("username", username);
        claims.put("role", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    @Override
    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
