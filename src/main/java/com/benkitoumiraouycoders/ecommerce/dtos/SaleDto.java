package com.benkitoumiraouycoders.ecommerce.dtos;

import com.benkitoumiraouycoders.ecommerce.entities.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaleDto {

    private Long id;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private String address;
    private String phone;
    private String email;
    private Double totalPrice;
    private SaleStatus saleStatus;

    public SaleDto(Long id, String address, String email, String phone, Double totalPrice, SaleStatus saleStatus,
                   LocalDateTime dateCreation, LocalDateTime dateUpdate
    ) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.saleStatus = saleStatus;
        this.totalPrice = totalPrice;
    }


}
