package aibles.userprofilemanager_1.dto;

import aibles.userprofilemanager_1.dto.UserProfileRequest;

public class UserProfileResponse extends UserProfileRequest {
    private Long id;

    // Constructors
    public UserProfileResponse() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserProfileResponse{" +
                "id=" + id +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}