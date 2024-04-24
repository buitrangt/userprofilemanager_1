package aibles.userprofilemanager_1.entity;


import jakarta.persistence.*;


@Entity
@Table(name="posts")

public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String title;
    private String content;


    @ManyToOne
    @JoinColumn(name="user_profile_id")
    private aibles.userprofilemanager_1.entity.UserProfileEntity UserProfile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public aibles.userprofilemanager_1.entity.UserProfileEntity getUserProfile() {
        return UserProfile;
    }

    public void setUserProfile(aibles.userprofilemanager_1.entity.UserProfileEntity userProfile) {
        UserProfile = userProfile;
    }


}

