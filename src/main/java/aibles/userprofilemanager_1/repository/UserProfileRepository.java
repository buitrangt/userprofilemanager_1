package aibles.userprofilemanager_1.repository;

import aibles.userprofilemanager_1.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, String> {
    Optional<UserProfileEntity> findByUsername(String username);
    Optional<UserProfileEntity> findByEmail(String email); // Thêm phương thức tìm kiếm theo email
}
