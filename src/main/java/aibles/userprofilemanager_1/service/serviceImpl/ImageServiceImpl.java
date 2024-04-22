package aibles.userprofilemanager_1.service.serviceImpl;

import aibles.userprofilemanager_1.dto.ImageRequest;
import aibles.userprofilemanager_1.dto.ImageResponse;
import aibles.userprofilemanager_1.entity.ImageEntity;
import aibles.userprofilemanager_1.exception.NotFoundException;
import aibles.userprofilemanager_1.repository.ImageRepository;
import aibles.userprofilemanager_1.service.mapping.ImageMapping;
import aibles.userprofilemanager_1.service.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    @Transactional
    public ImageResponse saveImage(ImageRequest request) {
        ImageEntity entity = ImageMapping.convertDtoToEntity(request);
        ImageEntity savedEntity = imageRepository.save(entity);
        return ImageMapping.convertEntityToImageResponse(savedEntity);
    }

    @Override
    public ImageResponse getImageById(Long id) {
        return imageRepository.findById(String.valueOf(id))
                .map(ImageMapping::convertEntityToImageResponse)
                .orElseThrow(() -> new NotFoundException("Image not found with ID: " + id));
    }

    @Override
    public List<ImageResponse> getAllImages() {
        return imageRepository.findAll().stream()
                .map(ImageMapping::convertEntityToImageResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ImageResponse updateImage(Long id, ImageRequest request) {
        ImageEntity entity = imageRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Image not found with ID: " + id));
        updateEntityFields(entity, request);
        imageRepository.save(entity);
        return ImageMapping.convertEntityToImageResponse(entity);
    }

    private void updateEntityFields(ImageEntity entity, ImageRequest request) {
        entity.setUrl(request.getUrl());
        // Add more fields to update as necessary
    }

    @Override
    @Transactional
    public void deleteImage(Long id) {
        if (!imageRepository.existsById(String.valueOf(id))) {
            throw new NotFoundException("Image not found with ID: " + id);
        }
        imageRepository.deleteById(String.valueOf(id));
    }
}
