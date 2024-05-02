package aibles.userprofilemanager_1.dto;

import aibles.userprofilemanager_1.dto.request.UserProfileRequest;

public class UserProfileResponse extends UserProfileRequest {
    private String id;
    private String role;

    // Constructors
    public UserProfileResponse(String id, String username, String email, String role) {
    }

    // Getters and Setters
    public String  getId() {
        return id;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserProfileResponse{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}