package aibles.userprofilemanager_1.service.mapping;

import aibles.userprofilemanager_1.dto.ImageRequest;
import aibles.userprofilemanager_1.dto.ImageResponse;
import aibles.userprofilemanager_1.entity.ImageEntity;

public class ImageMapping {
    public static ImageEntity convertDtoToEntity(ImageRequest dto) {
        ImageEntity entity = new ImageEntity();
        entity.setUrl(dto.getUrl());
        return entity;
    }

    public static ImageResponse convertEntityToImageResponse(ImageEntity entity) {
        ImageResponse dto = new ImageResponse();
        dto.setId(entity.getId());
        dto.setUrl(entity.getUrl());
        return dto;
    }
}
