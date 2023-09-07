package com.benkitoumiraouycoders.ecommerce.services;

import com.benkitoumiraouycoders.ecommerce.dao.ImageDao;
import com.benkitoumiraouycoders.ecommerce.dtos.ImageDto;
import com.benkitoumiraouycoders.ecommerce.entities.Image;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityAlreadyExistsException;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.mappers.ImageMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.ImageServiceInter;
import com.benkitoumiraouycoders.ecommerce.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService implements ImageServiceInter {
    private final ImageDao imageDao;
    private final ImageMapper imageMapper;

    @Override
    public List<ImageDto> getImagesByQuery(Long imageId, String imageName, String imageType, String imageFilePath, Long productId, Long categoryId) {
        return imageDao.findImagesByQuery(imageId, imageName, imageType, imageFilePath, productId, categoryId);
    }

    @Override
    public ImageDto getImageById(Long id) {

        Optional<Image> image = imageDao.findById(id);

        if (image.isPresent()) {
            return imageMapper.modelToDto(image.get());

        } else {
            throw new EntityNotFoundException(String.format("The image with the id %d is not found.", id));
        }
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
        // Delete the image file from the file system
        String filePath = image.getFilePath();
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
    private static String removeLastSegmentFromPath(String path) {
        int lastSlashIndex = path.lastIndexOf("/");
        if (lastSlashIndex >= 0) {
            return path.substring(0, lastSlashIndex);
        } else {
            // Handle the case where there is no slash in the path
            return path;
        }
    }

    private void deleteFolderAndContents(String folderPath){
        File folder = new File(folderPath);
        if (folder.exists()) {
            if (folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            // Recursively delete subdirectories
                            deleteFolderAndContents(file.getAbsolutePath());
                        } else {
                            // Delete files within the folder
                            if (!file.delete()) {
                                System.err.println("Failed to delete file: " + file.getAbsolutePath());
                            }
                        }
                    }
                }
                // Delete the empty folder
                if (!folder.delete()) {
                    System.err.println("Failed to delete folder: " + folder.getAbsolutePath());
                }
            } else {
                System.err.println("Path is not a directory: " + folder.getAbsolutePath());
            }
        } else {
            System.err.println("Folder does not exist: " + folder.getAbsolutePath());
        }
    }
    @Override
    public void deleteImagesByProductId(Long productId) {

        List<ImageDto> imageList = getImagesByQuery(null,null,null,null,productId, null);
        // Check if the list is not empty before accessing the first element
        if (!imageList.isEmpty()) {
            ImageDto image = imageList.get(0);
            String filePath = image.getFilePath();
            String newPath = removeLastSegmentFromPath(filePath);
            deleteFolderAndContents(newPath);
            imageDao.deleteAllByProductId(productId);
        } else {
            System.err.println("Image list is empty.");
        }

    }


}
