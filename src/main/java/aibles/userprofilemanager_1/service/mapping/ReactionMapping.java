package aibles.userprofilemanager_1.service.mapping;



import aibles.userprofilemanager_1.dto.ReactionRequest;
import aibles.userprofilemanager_1.dto.ReactionResponse;
import aibles.userprofilemanager_1.entity.ReactionEntity;

import aibles.userprofilemanager_1.repository.PostRepository;
import aibles.userprofilemanager_1.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReactionMapping {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public ReactionEntity convertDtoToEntity(ReactionRequest dto) {
        ReactionEntity entity = new ReactionEntity();
        entity.setPost(postRepository.findById(dto.getPostId()).orElse(null));
        entity.setUserProfile(userProfileRepository.findById(dto.getUserId()).orElse(null));
        entity.setType(dto.getType());
        return entity;
    }

    public ReactionResponse convertEntityToDto(ReactionEntity entity) {
        ReactionResponse dto = new ReactionResponse();
        dto.setId(entity.getId());
        dto.setPostId(entity.getPost().getId());
        dto.setUserId(entity.getUserProfile().getId());
        dto.setType(entity.getType());
        return dto;
    }
}
