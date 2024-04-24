package aibles.userprofilemanager_1.dto;

public class PostResponse extends PostRequest {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PostResponse{" +
                "id=" + id +
                '}';
    }
}
