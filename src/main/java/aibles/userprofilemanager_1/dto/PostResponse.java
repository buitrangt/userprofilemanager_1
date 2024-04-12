package aibles.userprofilemanager_1.dto;

public class PostResponse extends PostRequest {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PostResponse{" +
                "id=" + id +
                '}';
    }
}
