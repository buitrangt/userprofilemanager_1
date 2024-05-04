package aibles.userprofilemanager_1.service.service;

import aibles.userprofilemanager_1.dto.request.ImageRequest;
import aibles.userprofilemanager_1.dto.response.ImageResponse;

import java.util.List;

public interface ImageService {
    ImageResponse saveImage(ImageRequest request);
    ImageResponse getImageById(Long id);
    List<ImageResponse> getAllImages();
    ImageResponse updateImage(Long id, ImageRequest request);
    void deleteImage(Long id);
}
