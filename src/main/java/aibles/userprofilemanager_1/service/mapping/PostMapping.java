package aibles.userprofilemanager_1.service.mapping;

import aibles.userprofilemanager_1.dto.PostRequest;
import aibles.userprofilemanager_1.dto.PostResponse;
import aibles.userprofilemanager_1.entity.PostEntity;

public class PostMapping {
    public static PostEntity convertDtoEntity(PostRequest dto)
    {
        PostEntity entity=new PostEntity();

        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());

        return entity;
    }


    public static PostResponse convertEntityToPostResponse (PostEntity entity)
    {
        PostResponse dto=new PostResponse();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());

        return dto;
    }
}

