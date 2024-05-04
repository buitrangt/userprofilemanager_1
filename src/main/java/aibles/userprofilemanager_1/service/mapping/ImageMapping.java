package aibles.userprofilemanager_1.service.mapping;

import aibles.userprofilemanager_1.dto.request.ImageRequest;
import aibles.userprofilemanager_1.dto.response.ImageResponse;
import aibles.userprofilemanager_1.entity.ImageEntity;
import aibles.userprofilemanager_1.entity.PostEntity;

public class ImageMapping {
    public static ImageEntity convertDtoToEntity(String postId, ImageRequest dto) {
        ImageEntity entity = new ImageEntity();
        entity.setUrl(dto.getUrl());

        // Set the corresponding post for the image
        PostEntity post = new PostEntity();
        post.setId(postId);
        entity.setPost(post);

        return entity;
    }

    public static ImageResponse convertEntityToImageResponse(ImageEntity entity) {
        ImageResponse dto = new ImageResponse();
        dto.setId(entity.getId());
        dto.setUrl(entity.getUrl());
        return dto;
    }
}
