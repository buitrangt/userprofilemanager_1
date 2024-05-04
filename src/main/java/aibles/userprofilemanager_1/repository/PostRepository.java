package aibles.userprofilemanager_1.repository;


import aibles.userprofilemanager_1.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {

    Optional<PostEntity> findById(String id);

}
