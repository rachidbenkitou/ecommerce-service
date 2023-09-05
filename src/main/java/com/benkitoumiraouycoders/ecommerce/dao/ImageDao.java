package com.benkitoumiraouycoders.ecommerce.dao;

import com.benkitoumiraouycoders.ecommerce.dtos.ImageDto;
import com.benkitoumiraouycoders.ecommerce.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageDao extends JpaRepository<Image, Long> {
    @Query("SELECT NEW com.benkitoumiraouycoders.ecommerce.dtos.ImageDto(i.id, i.name,i.type, i.filePath, i.productId, i.categoryId) " +
            "FROM Image i " +
            "WHERE (:productId IS NULL OR i.productId = :productId)" +
            "AND (:categoryId IS NULL OR i.categoryId = :categoryId)" +
            "AND  (:imageId IS NULL OR i.id = :imageId)" +
            "AND  (:imageName IS NULL OR LOWER(i.name) LIKE LOWER(CONCAT('%', :imageName, '%')))" +
            "AND  (:imageType IS NULL OR LOWER(i.type) LIKE LOWER(CONCAT('%', :imageType, '%')))" +
            "AND  (:imageFilePath IS NULL OR LOWER(i.filePath) LIKE LOWER(CONCAT('%', :imageFilePath, '%')))")
    List<ImageDto> findImagesByQuery(
            @Param("imageId") Long imageId,
            @Param("imageName") String imageName,
            @Param("imageType") String imageType,
            @Param("imageFilePath") String imageFilePath,
            @Param("productId") Long productId,
            @Param("categoryId") Long categoryId
    );

    Optional<Image> findByName(String fileName);
    Optional<Image> findByFilePath(String filePath);
    void deleteAllByProductId(Long productId);
}
