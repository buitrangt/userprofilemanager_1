package aibles.userprofilemanager_1.controller;

import aibles.userprofilemanager_1.dto.ImageRequest;
import aibles.userprofilemanager_1.dto.ImageResponse;
import aibles.userprofilemanager_1.service.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImageResponse createImage(@RequestBody ImageRequest request) {
        try {
            return imageService.saveImage(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create image", e);
        }
    }

    @GetMapping("/{id}")
    public ImageResponse getImageById(@PathVariable Long id) {
        try {
            return imageService.getImageById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping
    public List<ImageResponse> getAllImages() {
        return imageService.getAllImages();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ImageResponse updateImage(@PathVariable Long id, @RequestBody ImageRequest request) {
        try {
            return imageService.updateImage(id, request);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to update image", e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImage(@PathVariable Long id) {
        try {
            imageService.deleteImage(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
