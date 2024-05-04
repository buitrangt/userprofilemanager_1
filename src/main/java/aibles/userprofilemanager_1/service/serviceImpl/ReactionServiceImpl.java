package aibles.userprofilemanager_1.service.serviceImpl;

import aibles.userprofilemanager_1.dto.request.ReactionRequest;
import aibles.userprofilemanager_1.dto.response.ReactionResponse;
import aibles.userprofilemanager_1.entity.PostEntity;
import aibles.userprofilemanager_1.entity.ReactionEntity;
import aibles.userprofilemanager_1.entity.UserProfileEntity;
import aibles.userprofilemanager_1.exception.NotFoundException;
import aibles.userprofilemanager_1.repository.ReactionRepository;
import aibles.userprofilemanager_1.service.mapping.ReactionMapping;
import aibles.userprofilemanager_1.service.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReactionServiceImpl implements ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private ReactionMapping reactionMapping;

    public ReactionServiceImpl(ReactionRepository reactionRepository) {
    }

    @Override
    @Transactional
    public ReactionResponse addReaction(ReactionRequest request) {
        ReactionEntity entity = reactionMapping.convertDtoToEntity(request);
        entity = reactionRepository.save(entity);
        return reactionMapping.convertEntityToDto(entity);
    }

    @Override
    public ReactionEntity addReaction(ReactionEntity reaction) {
        return null;
    }


    @Transactional
    public void removeReaction(Long reactionId) {
        if (!reactionRepository.existsById(String.valueOf(reactionId))) {
            throw new NotFoundException("Reaction not found with id: " + reactionId);
        }
        reactionRepository.deleteById(String.valueOf(reactionId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReactionResponse> getReactionsByPost(Long postId) {
        Collection<Object> reactions = reactionRepository.findByPost_Id(String.valueOf(postId));
        if (reactions.isEmpty()) {
            throw new NotFoundException("No reactions found for post with id: " + postId);
        }
        List<ReactionResponse> collect = reactions.stream()
                .map((Object entity) -> reactionMapping.convertEntityToDto((ReactionEntity) entity))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public boolean existsByPostAndUser(Long postId, Long userId) {
        return false;
    }


    @Override
    @Transactional(readOnly = true)
    public boolean existsByPostAndUserProfile(PostEntity post, UserProfileEntity userProfile) {
        return reactionRepository.existsByPostAndUserProfile(post, userProfile);
    }

    @Override
    public List<ReactionEntity> getReactionsByPost(PostEntity post) {
        return List.of();
    }

    @Override
    public boolean existsByPostAndUser(PostEntity post, UserProfileEntity user) {
        return false;
    }
}
