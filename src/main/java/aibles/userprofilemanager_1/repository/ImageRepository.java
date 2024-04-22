package aibles.userprofilemanager_1.repository;

import aibles.userprofilemanager_1.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, String> {


}
