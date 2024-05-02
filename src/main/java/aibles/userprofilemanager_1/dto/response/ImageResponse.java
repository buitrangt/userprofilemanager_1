package aibles.userprofilemanager_1.dto.response;

import aibles.userprofilemanager_1.dto.request.ImageRequest;

public class ImageResponse extends ImageRequest {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
