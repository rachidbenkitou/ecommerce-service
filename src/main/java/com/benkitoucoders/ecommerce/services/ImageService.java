package com.benkitoucoders.ecommerce.services;

import com.benkitoucoders.ecommerce.daos.ImageDao;
import com.benkitoucoders.ecommerce.dtos.ImageDto;
import com.benkitoucoders.ecommerce.entities.Image;
import com.benkitoucoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoucoders.ecommerce.mappers.ImageMapper;
import com.benkitoucoders.ecommerce.services.inter.ImageServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService implements ImageServiceInter {
    private final ImageDao imageDao;
    private final ImageMapper imageMapper;

/*    @Override
    public List<ImageDto> getImagesUrlsByProductIdByQuery(Long productId, Long imageId) {
        List<ImageDto> imageDtoList = imageDao.findImagesUrlsByProductId(productId, imageId);
        return Optional.ofNullable(imageDtoList)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ListIsEmptyException("The image list is empty"));
    }

    @Override
    public ImageDto getImageById(Long id) {
        List<ImageDto> imageDtoList = imageDao.findImagesUrlsByProductId(null, id);
        return imageDtoList.stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("There is no image with the provided ID."));
    }

    @Override
    public ImageDto getImageWhenIsPrimary(boolean isPrimary) {
        ImageDto imageDto = imageDao.getImageWhenIsPrimary(isPrimary).get(0);
        return Optional.ofNullable(imageDto).orElseThrow(() -> new EntityNotFoundException("There is no primary image."));
    }

    @Override
    public ImageDto addImage(ImageDto imageDto) {
        imageDto.setId(null);
        Image savedImage = imageDao.save(imageMapper.dtoToModel(imageDto));
        return imageMapper.modelToDto(savedImage);
    }

    @Override
    public ImageDto updateImage(Long id, ImageDto imageDto) {
        ImageDto oldImageDto = getImageById(id);
        imageDto.setId(oldImageDto.getId());
        Image updatedImage = imageDao.save(imageMapper.dtoToModel(imageDto));
        return imageMapper.modelToDto(updatedImage);
    }

    @Override
    public void deleteImageById(Long id) {
        getImageById(id);
        imageDao.deleteById(id);
    }*/
}
