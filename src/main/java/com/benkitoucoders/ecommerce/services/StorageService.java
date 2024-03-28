package com.benkitoucoders.ecommerce.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(MultipartFile file, Long productId) {
        String folderName = "products/product-" + productId;
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // Check if the products folder exists, and create it if not
        if (!s3Client.doesObjectExist(bucketName, folderName)) {
            s3Client.putObject(bucketName, folderName, "");
        }

        // Upload the file to the specified folder
        String key = folderName + "/" + fileName;
        s3Client.putObject(new PutObjectRequest(bucketName, key, convertMultiPartFileToFile(file)));

        return "File uploaded: " + key;
    }


    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
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

    public String getProductImages(Long productId) {
        // Create a request to generate a pre-signed URL for the S3 object
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, "products/product-16/1694475789238_image_300x400.jpg");

        // Generate the pre-signed URL without setting an expiration
        URL presignedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        return presignedUrl.toString();
    }
}
