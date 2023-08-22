package com.benkitoucoders.ecommerce.controllers;

import com.benkitoucoders.ecommerce.dtos.ImageDto;
import com.benkitoucoders.ecommerce.services.inter.ImageServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageServiceInter imageService;

    @GetMapping
    public ResponseEntity<List<ImageDto>> getImagesByQuery(@RequestParam(name = "imageId", required = false) Long imageId,
                                                           @RequestParam(name = "imageUrl", required = false) String imageUrl,
                                                           @RequestParam(name = "productId", required = false) Long productId) {
        return ResponseEntity.ok().body(imageService.getImagesByQuery(imageId, imageUrl, productId));
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

}
