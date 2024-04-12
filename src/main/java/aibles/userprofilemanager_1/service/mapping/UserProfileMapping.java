package aibles.userprofilemanager_1.service.mapping;

import aibles.userprofilemanager_1.dto.UserProfileRequest;
import aibles.userprofilemanager_1.dto.UserProfileResponse;
import aibles.userprofilemanager_1.entity.UserProfileEntity;

public class UserProfileMapping {
    public static UserProfileEntity convertDtoToEntity(UserProfileRequest dto) {
        UserProfileEntity entity = new UserProfileEntity();
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public static UserProfileResponse convertEntityToDto(UserProfileEntity entity) {
        UserProfileResponse dto = new UserProfileResponse();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}
