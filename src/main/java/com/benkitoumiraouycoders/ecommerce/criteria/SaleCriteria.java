package com.benkitoumiraouycoders.ecommerce.criteria;

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
    private String phone;
    private String email;
    private Long saleStatusId;


}
