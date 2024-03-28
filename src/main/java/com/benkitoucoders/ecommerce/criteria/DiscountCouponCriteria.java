package com.benkitoucoders.ecommerce.criteria;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data

public class DiscountCouponCriteria implements Serializable {

    private Long id;
    private String code;
    private String discountType;
    private String active;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long packageId;
    private Long productId;
    private LocalDateTime dateCreation;
    private Long userCreation;
    private LocalDateTime dateUpdate;
    private Long userUpdate;

}
