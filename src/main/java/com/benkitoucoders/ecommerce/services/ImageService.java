package com.benkitoucoders.ecommerce.services;

import com.benkitoucoders.ecommerce.daos.ImageDao;
import com.benkitoucoders.ecommerce.dtos.ImageDto;
import com.benkitoucoders.ecommerce.entities.Image;
import com.benkitoucoders.ecommerce.exceptions.EntityAlreadyExistsException;
import com.benkitoucoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoucoders.ecommerce.mappers.ImageMapper;
import com.benkitoucoders.ecommerce.services.inter.ImageServiceInter;
import com.benkitoucoders.ecommerce.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService implements ImageServiceInter {
    private final ImageDao imageDao;
    private final ImageMapper imageMapper;

    @Override
    public List<ImageDto> getImagesByQuery(Long imageId, String imageName, String imageType, String imageFilePath, Long productId) {
        List<ImageDto> imageDtoList = imageDao.findImagesByQuery(imageId, imageName, imageType, imageFilePath, productId);
        return Optional.ofNullable(imageDtoList)
                .filter(list -> !list.isEmpty())
                .orElse(Collections.emptyList());
    }

    @Override
    public ImageDto getImageById(Long id) {
        List<ImageDto> imageDtoList = imageDao.findImagesByQuery(id, null, null, null, null);
        return imageDtoList.stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no image with the ID %d.", id)));
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
        ImageDto image = getImageById(id); // Make sure this method retrieves the image entity

        System.out.println(image);

        // Delete the image file from the file system
        String filePath = image.getFilePath();

        System.out.println(filePath);

        File imageFile = new File(filePath);

        if (imageFile.exists()) {
            if (imageFile.delete()) {
                imageDao.deleteById(id);
            } else {
                throw new RuntimeException("Failed to delete image file");
            }
        } else {
            // If the file doesn't exist, only delete the entity from the database
            imageDao.deleteById(id);
        }
    }



    @Override
    public String uploadImageToFileSystem(MultipartFile file, Long productId) throws IOException {
        String folderPath = Constants.FOLDER_PATH + "product_" + productId + "/";
        String filePath = folderPath + file.getOriginalFilename();

        Optional<Image> existingImage = imageDao.findByFilePath(filePath);
        existingImage.ifPresent(image -> {
            throw new EntityAlreadyExistsException(String.format("This image with name %s already exists", file.getOriginalFilename()));
        });

        Image image = imageDao.save(
                Image.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .filePath(filePath).build());

        // Create the product's folder if it doesn't exist
        Path path = Paths.get(folderPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        if (image != null) {
            file.transferTo(new File(filePath));
            return "file uploaded successfully: " + filePath;
        } else {
            return "Failed to save image entity.";
        }
    }


    @Override
    public void uploadImagesToFileSystem(List<MultipartFile> images, Long productId) throws IOException {
        for (MultipartFile image : images) {
            uploadImageToFileSystem(image, productId);
        }
    }

}
