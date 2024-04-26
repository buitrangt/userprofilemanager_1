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
    private String category;
    private String parentId;
    private String authorId;
    private Integer sharedCount =0;



    @ManyToOne
    @JoinColumn(name="user_profile_id")
    private aibles.userprofilemanager_1.entity.UserProfileEntity UserProfile;

    public String getAuthorId() {
        return authorId;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParentId() {
        return parentId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public Integer getSharedCount() {
        return sharedCount;
    }

    public void setSharedCount(Integer sharedCount) {
        this.sharedCount = sharedCount;
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

