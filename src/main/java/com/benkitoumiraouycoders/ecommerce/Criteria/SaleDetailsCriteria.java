package com.benkitoumiraouycoders.ecommerce.Criteria;

import com.benkitoumiraouycoders.ecommerce.dtos.PackageDto;
import com.benkitoumiraouycoders.ecommerce.dtos.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaleDetailsCriteria {

    private Long id;
    private Long saleId;
    private Long productId;
    private Long packageId;


}
