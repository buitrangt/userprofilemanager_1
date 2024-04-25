package aibles.userprofilemanager_1.service.mapping;

import aibles.userprofilemanager_1.dto.CommentRequest;
import aibles.userprofilemanager_1.dto.CommentResponse;
import aibles.userprofilemanager_1.entity.CommentEntity;
import aibles.userprofilemanager_1.entity.PostEntity;
import aibles.userprofilemanager_1.entity.UserProfileEntity;
import aibles.userprofilemanager_1.repository.PostRepository;
import aibles.userprofilemanager_1.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapping {

    @Autowired
    private static PostRepository postRepository;
    @Autowired
    private static UserProfileRepository userProfileRepository;

    public static CommentEntity convertDtoToEntity(CommentRequest dto) {
        CommentEntity entity = new CommentEntity();
        entity.setContent(dto.getContent());

        // Assuming Post and UserProfile IDs are provided in the DTO and need to be resolved
        PostEntity post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + dto.getPostId()));
        UserProfileEntity user = userProfileRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        entity.setPost(post);
        entity.setUser(user);

        return entity;
    }

    public CommentResponse convertEntityToDto(CommentEntity entity) {
        CommentResponse dto = new CommentResponse();
        dto.setId(entity.getId());
        dto.setPostId(entity.getPost().getId());
        dto.setUserId(entity.getUser().getId());
        dto.setContent(entity.getContent());

        return dto;
    }
}
