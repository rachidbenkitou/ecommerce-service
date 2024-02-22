package com.benkitoumiraouycoders.ecommerce.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.benkitoumiraouycoders.ecommerce.dao.ImageDao;
import com.benkitoumiraouycoders.ecommerce.dtos.ImageDto;
import com.benkitoumiraouycoders.ecommerce.entities.Image;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityAlreadyExistsException;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.mappers.ImageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements com.benkitoumiraouycoders.ecommerce.services.inter.ImageService {
    private final ImageDao imageDao;
    private final ImageMapper imageMapper;
    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Override
    public List<ImageDto> getImagesByQuery(Long imageId, String imageName, String imageType, String imageFilePath, Long productId, Long categoryId) {
        return imageDao.findImagesByQuery(imageId, imageName, imageType, imageFilePath, productId, categoryId);
    }

    @Override
    public void uploadCategoryImage(MultipartFile file, Long categoryId) throws IOException {

        String folderName = "categories/category-" + categoryId;
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = folderName + "/" + fileName;

        Optional<Image> existingImage = imageDao.findByFilePath(filePath);
        existingImage.ifPresent(image -> {
            throw new EntityAlreadyExistsException(String.format("This image with path %s already exists", filePath));
        });

        imageDao.save(
                Image.builder()
                        .name(file.getOriginalFilename())
                        .productId(null)
                        .categoryId(categoryId)
                        .type(file.getContentType())
                        .filePath(filePath).build());

        // Check if the products folder exists, and create it if not
        if (!s3Client.doesObjectExist(bucketName, folderName)) {
            s3Client.putObject(bucketName, folderName + "/", "");
        }

        // Upload the file to the specified folder
        String key = folderName + "/" + fileName;
        s3Client.putObject(new PutObjectRequest(bucketName, key, convertMultiPartFileToFile(file)));
    }

    @Override
    public void uploadProductImage(MultipartFile file, Long productId) throws IOException {
        String folderName = "products/product-" + productId;
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        String filePath = folderName + "/" + fileName;

        Optional<Image> existingImage = imageDao.findByFilePath(filePath);
        existingImage.ifPresent(image -> {
            throw new EntityAlreadyExistsException(String.format("This image with path %s already exists", fileName));
        });

        imageDao.save(
                Image.builder()
                        .name(file.getOriginalFilename())
                        .productId(productId)
                        .categoryId(null)
                        .type(file.getContentType())
                        .filePath(filePath).build());

        // Check if the specific folder exists, and create it if not
        if (!s3Client.doesObjectExist(bucketName, folderName)) {
            s3Client.putObject(bucketName, folderName + "/", ""); // Note the trailing '/'
        }

        // Upload the file to the specific folder
        String key = folderName + "/" + fileName;
        s3Client.putObject(new PutObjectRequest(bucketName, key, convertMultiPartFileToFile(file)));
    }

    @Override
    public void uploadProductImages(List<MultipartFile> images, Long productId) throws IOException {
        for (MultipartFile image : images) {
            uploadProductImage(image, productId);
        }
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
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
    public ResponseDto deleteImageById(Long id) {
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

        return ResponseDto.builder()
                .message("Image successfully deleted.")
                .build();
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

    private ResponseDto deleteImagesFromS3(String basePath) {
        try {
            // List objects in the specified path
            ObjectListing objectListing = s3Client.listObjects(bucketName, basePath);

            // Iterate through objects and delete them
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                String key = objectSummary.getKey();
                s3Client.deleteObject(bucketName, key);
            }

            // Delete the folder itself if it's empty
            if (objectListing.getObjectSummaries().isEmpty()) {
                s3Client.deleteObject(bucketName, basePath);
            }

            return ResponseDto.builder()
                    .message("Images successfully deleted.")
                    .build();
        } catch (AmazonS3Exception e) {
            // Handle exceptions here (e.g., object not found)
            return ResponseDto.builder()
                    .message("Error deleting images: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto deleteImagesByProductId(Long productId) {
        List<ImageDto> imageList = getImagesByQuery(null, null, null, null, productId, null);
        return deleteImagesFromBDAndAws(imageList, productId, "product");
    }

    @Override
    public ResponseDto deleteImageByCategoryId(Long categoryId) {
        List<ImageDto> imageList = getImagesByQuery(null, null, null, null, null, categoryId);
        return deleteImagesFromBDAndAws(imageList, categoryId, "category");
    }

    private ResponseDto deleteImagesFromBDAndAws(List<ImageDto> imageList, Long elementId, String elementNme) {
        // Check if the list is not empty before accessing the first element
        if (!imageList.isEmpty()) {
            ImageDto image = imageList.get(0);
            String filePath = image.getFilePath();
            String newPath = removeLastSegmentFromPath(filePath);
            //deleteFolderAndContents(newPath);
            deleteImagesFromS3(newPath);
            if (elementNme.equals("category")) {
                imageDao.deleteImageByCategoryId(elementId);
            } else if (elementNme.equals("product")) {
                imageDao.deleteAllByProductId(elementId);
            }
        }
        return ResponseDto.builder()
                .message("Images successfully deleted.")
                .build();
    }

    @Override
    public String getImagesUrlsFromAws(String imagePath) {
        // Create a request to generate a pre-signed URL for the S3 object
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, imagePath);

        // Generate the pre-signed URL without setting an expiration
        URL presignedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        return presignedUrl.toString();
    }

    @Override
    public List<String> getImagesFromAwsInFolder(String folderPath) {
        List<String> imageUrls = new ArrayList<>();
        //folderPath = "products/product-25"; // You can remove this line if you want to use the folderPath parameter

        // Ensure folderPath ends with "/"
        if (folderPath != null) {
            if (!folderPath.endsWith("/")) {
                folderPath += "/";
            }
        }
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withBucketName(bucketName)
                .withPrefix(folderPath); // List objects with the given prefix (folderPath)

        ObjectListing objectListing;
        do {
            objectListing = s3Client.listObjects(listObjectsRequest);
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                // Generate the pre-signed URL for each object and add it to the list of image URLs
                String key = objectSummary.getKey();
                String url = getImagesUrlsFromAws(key);
                imageUrls.add(url);
            }
            listObjectsRequest.setMarker(objectListing.getNextMarker());
        } while (objectListing.isTruncated());

        // Now you have a list of image URLs in the specified folder
        return imageUrls;
    }


    private void deleteFolderAndContents(String folderPath) {
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
    public ResponseDto deleteImageByFilePathFromLocalSystem(String path) {
        Optional<Image> image = imageDao.findByFilePath(path); // Make sure this method retrieves the image entity
        // Delete the image file from the file system
        //File imageFile = new File(path);
//        if (imageFile.exists()) {
//            if (imageFile.delete()) {
//                imageDao.deleteAllByFilePath(path);
//            } else {
//                throw new RuntimeException("Failed to delete image file");
//            }
//        } else {
//            // If the file doesn't exist, only delete the entity from the database
//            imageDao.deleteAllByFilePath(path);
//        }

        String newFolderPath = removeLastSegmentFromPath(path);

        deleteFolderAndContents(newFolderPath);
        imageDao.deleteAllByFilePath(path);
        return ResponseDto.builder()
                .message("Image successfully deleted.")
                .build();
    }
}
