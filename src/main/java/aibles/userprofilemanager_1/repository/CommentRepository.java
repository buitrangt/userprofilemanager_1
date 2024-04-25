package aibles.userprofilemanager_1.repository;

import aibles.userprofilemanager_1.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByPostId(Long postId);
}