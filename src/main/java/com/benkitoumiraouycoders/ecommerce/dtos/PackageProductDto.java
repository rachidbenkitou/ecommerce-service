package com.benkitoumiraouycoders.ecommerce.dtos;

import com.benkitoumiraouycoders.ecommerce.entities.Package;
import com.benkitoumiraouycoders.ecommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageProductDto {
    private Long id;
    private Long packageId;
    private Long productId;
    private Long productQuantity;
    private LocalDateTime dateCreation;
    private Long userCreation;
    private LocalDateTime dateUpdate;
    private Long userUpdate;
    private ProductDto productDto;
    private PackageDto aPackageDto;


    public PackageProductDto(Long id, Long packageId, Long productId,
                             LocalDateTime dateCreation, Long userCreation,
                             LocalDateTime dateUpdate, Long userUpdate) {
        this.id = id;
        this.packageId = packageId;
        this.productId = productId;
        this.dateCreation = dateCreation;
        this.userCreation = userCreation;
        this.dateUpdate = dateUpdate;
        this.userUpdate = userUpdate;
    }

}
