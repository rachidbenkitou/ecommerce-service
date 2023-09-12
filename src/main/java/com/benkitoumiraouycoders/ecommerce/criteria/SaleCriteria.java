package com.benkitoumiraouycoders.ecommerce.criteria;

import com.benkitoumiraouycoders.ecommerce.entities.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaleCriteria {

    private Long id;
    private String cni;
    private String address;
    private String isPayed;
    private  String phone ;
    private String email;
    private SaleStatus saleStatus;



}
