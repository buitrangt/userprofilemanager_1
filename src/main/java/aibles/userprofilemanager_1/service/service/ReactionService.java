package aibles.userprofilemanager_1.service.service;


import aibles.userprofilemanager_1.dto.request.ReactionRequest;
import aibles.userprofilemanager_1.dto.response.ReactionResponse;
import aibles.userprofilemanager_1.entity.PostEntity;
import aibles.userprofilemanager_1.entity.ReactionEntity;
import aibles.userprofilemanager_1.entity.UserProfileEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReactionService {
    ReactionResponse addReaction(ReactionRequest request);

    @Transactional
    ReactionEntity addReaction(ReactionEntity reaction);

    void removeReaction(Long reactionId);
    List<ReactionResponse> getReactionsByPost(Long postId);
    boolean existsByPostAndUser(Long postId, Long userId);

    @Transactional(readOnly = true)
    boolean existsByPostAndUserProfile(PostEntity post, UserProfileEntity userProfile);

    @Transactional(readOnly = true)
    List<ReactionEntity> getReactionsByPost(PostEntity post);

    @Transactional(readOnly = true)
    boolean existsByPostAndUser(PostEntity post, UserProfileEntity user);
}


