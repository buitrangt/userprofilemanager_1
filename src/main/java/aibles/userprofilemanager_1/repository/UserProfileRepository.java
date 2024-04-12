package aibles.userprofilemanager_1.repository;


import aibles.userprofilemanager_1.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity,Long> {
}
