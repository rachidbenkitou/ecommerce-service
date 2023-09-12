package com.benkitoumiraouycoders.ecommerce.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
