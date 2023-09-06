package com.benkitoumiraouycoders.ecommerce.dtos;

import com.benkitoumiraouycoders.ecommerce.entities.SaleStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long userCreation;
    private LocalDateTime dateUpdate;
    private Long userUpdate;
    private String cni;
    private String address;
    private  String phone ;
    private String email;
    private String isPayed;
    private SaleStatus saleStatus;
    public SaleDto(Long id, LocalDateTime dateCreation, Long userUpdate, LocalDateTime dateUpdate, Long userCreation,
                   String address, String cni, String email, String phone, SaleStatus saleStatus, String isPayed) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.userUpdate = userUpdate;
        this.dateUpdate = dateUpdate;
        this.userCreation = userCreation;
        this.address = address;
        this.cni = cni;
        this.email = email;
        this.phone = phone;
        this.saleStatus = saleStatus;
        this.isPayed = isPayed;
    }




}
