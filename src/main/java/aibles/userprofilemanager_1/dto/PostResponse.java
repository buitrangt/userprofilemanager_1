package aibles.userprofilemanager_1.dto;

public class PostResponse extends PostRequest {
    private String id;
    private Integer sharedCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSharedCount() {
        return sharedCount;
    }

    public void setSharedCount(Integer sharedCount) {
        this.sharedCount = sharedCount;
    }

    @Override
    public String toString() {
        return "PostResponse{" +
                "id='" + id + '\'' +
                ", sharedCount=" + sharedCount +
                '}';
    }
}
