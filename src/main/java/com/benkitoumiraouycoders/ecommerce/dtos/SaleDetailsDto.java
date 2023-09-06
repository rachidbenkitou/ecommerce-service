package com.benkitoumiraouycoders.ecommerce.dtos;

import com.benkitoumiraouycoders.ecommerce.entities.Package;
import com.benkitoumiraouycoders.ecommerce.entities.Product;
import com.benkitoumiraouycoders.ecommerce.entities.Sale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaleDetailsDto {

    private Long id;
    private Long saleId;
    private Long productId;
    private Long packageId;
    private Long packageQuantity;
    private Long productQuantity;
    private LocalDateTime dateCreation;
    private Long userCreation;
    private LocalDateTime dateUpdate;
    private Long userUpdate;
    private ProductDto productDto;
    private SaleDto saleDto;
    private PackageDto pPackageDto;
    public SaleDetailsDto(Long id, LocalDateTime dateCreation, Long userUpdate, LocalDateTime dateUpdate, Long userCreation,
                          Long packageId, Long productId, Long packageQuantity, Long productQuantity, Long saleId) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.userUpdate = userUpdate;
        this.dateUpdate = dateUpdate;
        this.userCreation = userCreation;
        this.packageId = packageId;
        this.productId = productId;
        this.packageQuantity = packageQuantity;
        this.productQuantity = productQuantity;
        this.saleId = saleId;
    }
}
