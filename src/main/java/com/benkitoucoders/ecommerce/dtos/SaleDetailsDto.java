package com.benkitoucoders.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDateTime dateUpdate;
    private SaleDto saleDto;
    private PackageDto packageDto;
    public SaleDetailsDto(Long id,
                          Long productId, Long productQuantity, Long saleId, LocalDateTime dateCreation, LocalDateTime dateUpdate) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.saleId = saleId;
    }
}
