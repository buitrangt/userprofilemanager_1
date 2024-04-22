package aibles.userprofilemanager_1.dto;

public class UserProfileResponse extends UserProfileRequest {
    private Long id;
    private String role;

    // Constructors
    public UserProfileResponse(Long id, String username, String email, String role) {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(Long id) {
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