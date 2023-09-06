package com.benkitoumiraouycoders.ecommerce.Criteria;

import com.benkitoumiraouycoders.ecommerce.dtos.PackageDto;
import com.benkitoumiraouycoders.ecommerce.dtos.ProductDto;
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
public class PackageProductCriteria {

    private Long id;
    private Long packageId;
    private Long productId;
    private Long productQuantity;
    private LocalDateTime dateCreation;
    private Long userCreation;
    private LocalDateTime dateUpdate;
    private Long userUpdate;

}
