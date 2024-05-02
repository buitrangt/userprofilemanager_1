package aibles.userprofilemanager_1.dto.request;

public class ImageRequest {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageRequest{" +
                "url='" + url + '\'' +
                '}';
    }
}
