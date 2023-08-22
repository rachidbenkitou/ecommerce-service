package com.benkitoucoders.ecommerce.services.inter;

import com.benkitoucoders.ecommerce.dtos.ImageDto;

import java.util.List;

public interface ImageServiceInter {
    List<ImageDto> getImagesByQuery(Long imageId, String imageUrl, Long productId);

    ImageDto getImageById(Long id);

    ImageDto addImage(ImageDto imageDto);

    ImageDto updateImage(Long id, ImageDto imageDto);

    void deleteImageById(Long id);
}
