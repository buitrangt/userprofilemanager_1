package aibles.userprofilemanager_1.dto;

public class ImageResponse extends ImageRequest {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "id=" + id +
                ", url='" + getUrl() + '\'' +  // Inherits getUrl from ImageRequest
                '}';
    }
}
