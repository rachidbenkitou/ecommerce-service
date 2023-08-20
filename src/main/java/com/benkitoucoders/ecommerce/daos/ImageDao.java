package com.benkitoucoders.ecommerce.daos;

import com.benkitoucoders.ecommerce.dtos.ImageDto;
import com.benkitoucoders.ecommerce.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageDao extends JpaRepository<Image, Long> {
/*    @Query("SELECT NEW com.benkitoucoders.ecommerce.dtos.ImageDto(i.id, i.url) FROM Image i WHERE (:productId IS NULL OR i.productId = :productId)" +
            "AND  (:imageId IS NULL OR i.id = :imageId)")
    List<ImageDto> findImagesUrlsByProductId(@Param("productId") Long productId, @Param("imageId") Long imageId);

    @Query("SELECT NEW com.benkitoucoders.ecommerce.dtos.ImageDto(i.id, i.url) FROM Image i WHERE i.isPrimary= :isPrimary")
    List<ImageDto> getImageWhenIsPrimary(@Param("isPrimary") boolean isPrimary);*/
}
