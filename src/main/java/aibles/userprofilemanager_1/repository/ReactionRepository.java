package aibles.userprofilemanager_1.repository;



import aibles.userprofilemanager_1.entity.PostEntity;
import aibles.userprofilemanager_1.entity.ReactionEntity;
import aibles.userprofilemanager_1.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ReactionRepository extends JpaRepository<ReactionEntity, String> {
    List<ReactionEntity> findByPost(PostEntity post);
    List<ReactionEntity> findByUserProfile(UserProfileEntity user);
    boolean existsByPostAndUserProfile(PostEntity post, UserProfileEntity user);

    Collection<Object> findByPost_Id(String  postId);
}

