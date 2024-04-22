package aibles.userprofilemanager_1.service.serviceImpl;

import aibles.userprofilemanager_1.dto.UserProfileRequest;
import aibles.userprofilemanager_1.dto.UserProfileResponse;
import aibles.userprofilemanager_1.entity.UserProfileEntity;
import aibles.userprofilemanager_1.exception.NotFoundException;
import aibles.userprofilemanager_1.repository.UserProfileRepository;
import aibles.userprofilemanager_1.service.mapping.UserProfileMapping;
import aibles.userprofilemanager_1.service.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfileResponse createUserProfile(UserProfileRequest request) {
        UserProfileEntity entity = UserProfileMapping.convertDtoToEntity(request);
        UserProfileEntity savedEntity = userProfileRepository.save(entity);
        return UserProfileMapping.convertEntityToDto(savedEntity);
    }

    @Override
    public UserProfileResponse updateUserProfile(Long id, UserProfileRequest request) {
        UserProfileEntity entity = userProfileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User profile not found for this id: " + id));
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        UserProfileEntity updatedEntity = userProfileRepository.save(entity);
        return UserProfileMapping.convertEntityToDto(updatedEntity);
    }

    @Override
    public void deleteUserProfile(Long id) {
        if (!userProfileRepository.existsById(id)) {
            throw new NotFoundException("User profile not found for this id: " + id);
        }
        userProfileRepository.deleteById(id);
    }

    @Override
    public UserProfileResponse getUserProfileById(Long id) {
        UserProfileEntity entity = userProfileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User profile not found for this id: " + id));
        return UserProfileMapping.convertEntityToDto(entity);
    }

    @Override
    public List<UserProfileResponse> getAllUserProfiles() {
        List<UserProfileEntity> entities = userProfileRepository.findAll();
        return entities.stream()
                .map(UserProfileMapping::convertEntityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public UserProfileResponse getUserProfileByUsername(String username) {
        // Giả sử chúng ta có một phương thức trong repository để tìm người dùng theo username
        return userProfileRepository.findByUsername(username)
                .map(user -> new UserProfileResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRole()))
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }


}
