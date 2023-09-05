package com.benkitoumiraouycoders.ecommerce.controllers;

import com.benkitoumiraouycoders.ecommerce.dtos.ImageDto;
import com.benkitoumiraouycoders.ecommerce.services.inter.ImageServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:58213", allowCredentials = "true")
public class ImageController {

    private final ImageServiceInter imageService;

    @GetMapping
    public ResponseEntity<List<ImageDto>> getImagesByQuery(@RequestParam(name = "imageId", required = false) Long imageId,
                                                           @RequestParam(name = "imageName", required = false) String imageName,
                                                           @RequestParam(name = "imageType", required = false) String imageType,
                                                           @RequestParam(name = "imageFilePath", required = false) String imageFilePath,
                                                           @RequestParam(name = "productId", required = false) Long productId,
                                                           @RequestParam(name = "categoryId", required = false) Long categoryId
    ) {
        return ResponseEntity.ok().body(imageService.getImagesByQuery(imageId, imageName, imageType, imageFilePath, productId, categoryId));
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<ImageDto> getImageById(@PathVariable Long imageId) {
        return ResponseEntity.ok().body(imageService.getImageById(imageId));
    }

    @PostMapping
    public ResponseEntity<ImageDto> addImage(@RequestBody ImageDto imageDto) {
        return ResponseEntity.ok().body(imageService.addImage(imageDto));
    }

    @PutMapping("/{imageId}")
    public ResponseEntity<ImageDto> updateImage(@PathVariable Long imageId, @RequestBody ImageDto imageDto) {
        return ResponseEntity.ok().body(imageService.updateImage(imageId, imageDto));
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<String> deleteImageById(@PathVariable Long imageId) {
        imageService.deleteImageById(imageId);
        return ResponseEntity.ok().body("The category has been deleted successfully.");
    }

    @DeleteMapping("/byProduct/{productId}")
    public ResponseEntity<?> deleteImageByProductId(@PathVariable Long productId) {
        imageService.deleteImagesByProductId(productId);
        return ResponseEntity.ok().body(null);
    }

}
