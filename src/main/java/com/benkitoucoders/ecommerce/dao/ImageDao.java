package com.benkitoucoders.ecommerce.dao;

import com.benkitoucoders.ecommerce.dtos.ImageDto;
import com.benkitoucoders.ecommerce.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageDao extends JpaRepository<Image, Long> {
    @Query("SELECT NEW com.benkitoucoders.ecommerce.dtos.ImageDto(i.id, i.name,i.type, i.filePath, i.productId) " +
            "FROM Image i " +
            "WHERE (:productId IS NULL OR i.productId = :productId)" +
            "AND  (:imageId IS NULL OR i.id = :imageId)" +
            "AND  (:imageName IS NULL OR LOWER(i.name) LIKE LOWER(CONCAT('%', :imageName, '%')))" +
            "AND  (:imageType IS NULL OR LOWER(i.type) LIKE LOWER(CONCAT('%', :imageType, '%')))" +
            "AND  (:imageFilePath IS NULL OR LOWER(i.filePath) LIKE LOWER(CONCAT('%', :imageFilePath, '%')))")
    List<ImageDto> findImagesByQuery(
            @Param("imageId") Long imageId,
            @Param("imageName") String imageName,
            @Param("imageType") String imageType,
            @Param("imageFilePath") String imageFilePath,
            @Param("productId") Long productId
    );

    Optional<Image> findByName(String fileName);
    Optional<Image> findByFilePath(String filePath);
}
