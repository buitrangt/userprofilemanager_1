package aibles.userprofilemanager_1.service.serviceImpl;

import aibles.userprofilemanager_1.dto.PostRequest;
import aibles.userprofilemanager_1.dto.PostResponse;
import aibles.userprofilemanager_1.entity.PostEntity;
import aibles.userprofilemanager_1.repository.PostRepository;
import aibles.userprofilemanager_1.service.mapping.PostMapping;
import aibles.userprofilemanager_1.service.service.PostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository)
    {
        this.postRepository=postRepository;
    }

    @Override
    @Transactional
    public PostResponse create(PostRequest request)
    {
        PostEntity entity= PostMapping.convertDtoEntity(request);
        PostEntity saveEntity=postRepository.save(entity);
        return PostMapping.convertEntityToPostResponse(saveEntity);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        List<PostEntity> entities = postRepository.findAll();
        return entities.stream().map(PostMapping::convertEntityToPostResponse).collect(Collectors.toList());
    }

    @Override
    //@Transactional(readOnly = true)
    public PostResponse getPostById(Long id)
    {
        Optional<PostEntity> entity=postRepository.findById(id);
        if(entity.isPresent())
        {
            return PostMapping.convertEntityToPostResponse(entity.get());
        }
        else {
            throw new RuntimeException("Post not found with id: "+id);
        }
    }

    @Override
    @Transactional
    public PostResponse updatePosts(Long id,PostRequest request)
    {
        Optional<PostEntity> existingEntity = postRepository.findById(id);
        if(existingEntity.isPresent())
        {
            PostEntity updatedEntity = existingEntity.get();
            updatedEntity.setTitle(request.getTitle());
            updatedEntity.setContent(request.getContent());
            PostEntity savedEntity = postRepository.save(updatedEntity);
            return PostMapping.convertEntityToPostResponse(savedEntity);
        }
        else
        {
            throw new RuntimeException("Post not found with id: "+id);
        }
    }

    @Override
    @Transactional
    public void deletePost(Long id)
    {
        postRepository.deleteById(id);
    }

}
