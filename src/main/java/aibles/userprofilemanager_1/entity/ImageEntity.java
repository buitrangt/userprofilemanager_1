package aibles.userprofilemanager_1.entity;


import jakarta.persistence.*;


@Entity
@Table(name="images")

public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String url;

    @ManyToOne
    @JoinColumn(name="post_id")
    private PostEntity post;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}
