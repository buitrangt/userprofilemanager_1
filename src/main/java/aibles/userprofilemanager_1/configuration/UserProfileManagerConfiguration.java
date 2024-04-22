package aibles.userprofilemanager_1.configuration;

import aibles.userprofilemanager_1.repository.ImageRepository;
import aibles.userprofilemanager_1.repository.UserProfileRepository;
import aibles.userprofilemanager_1.repository.PostRepository;
import aibles.userprofilemanager_1.service.service.ImageService;
import aibles.userprofilemanager_1.service.service.UserProfileService;
import aibles.userprofilemanager_1.service.service.PostService;
import aibles.userprofilemanager_1.service.serviceImpl.ImageServiceImpl;
import aibles.userprofilemanager_1.service.serviceImpl.PostServiceImpl;
import aibles.userprofilemanager_1.service.serviceImpl.UserProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserProfileManagerConfiguration {

    private final ImageRepository imageRepository;
    private final UserProfileRepository userProfileRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserProfileManagerConfiguration(
            ImageRepository imageRepository,
            UserProfileRepository userProfileRepository,
            PostRepository postRepository) {
        this.imageRepository = imageRepository;
        this.userProfileRepository = userProfileRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public ImageService imageService() {
        return new ImageServiceImpl(imageRepository);
    }

    @Bean
    public UserProfileService userProfileService() {
        return new UserProfileServiceImpl(userProfileRepository);
    }

    @Bean
    public PostService postService() {
        return new PostServiceImpl(postRepository);
    }
}
