package aibles.userprofilemanager_1.service.service;



/**
 * Interface for authentication token management services.
 * Defines methods to generate and validate access and refresh JSON Web Tokens (JWTs).
 */
public interface AuthTokenService {

    /**
     * Generates an access token based on user's details.
     *
     * @param userId the user's ID
     * @param email the user's email
     * @param username the user's username
     * @param role the user's role
     * @return a JWT as a String
     */
    String generateAccessToken(String userId, String email, String username, String role);

    /**
     * Extracts the subject (user ID) from the access token.
     *
     * @param token the JWT token
     * @return the user ID embedded in the token
     */
    String getSubjectFromAccessToken(String token);

    /**
     * Validates an access token by comparing the embedded user ID and checking if it's expired.
     *
     * @param token the JWT token
     * @param userId the user ID to validate against the token
     * @return true if the token is valid, otherwise false
     */
    boolean validateAccessToken(String token, String userId);

    /**
     * Generates a refresh token based on user's details.
     *
     * @param userId the user's ID
     * @param email the user's email
     * @param username the user's username
     * @param role the user's role
     * @return a JWT as a String
     */
    String generateRefreshToken(String userId, String email, String username, String role);

    /**
     * Extracts the subject (user ID) from the refresh token.
     *
     * @param token the JWT token
     * @return the user ID embedded in the token
     */
    String getSubjectFromRefreshToken(String token);

    /**
     * Validates a refresh token by comparing the embedded user ID and checking if it's expired.
     *
     * @param token the JWT token
     * @param userId the user ID to validate against the token
     * @return true if the token is valid, otherwise false
     */
    boolean validateRefreshToken(String token, String userId);
}

