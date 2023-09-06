package com.benkitoumiraouycoders.ecommerce.dtos;


import com.benkitoumiraouycoders.ecommerce.entities.DiscountCouponType;
import com.benkitoumiraouycoders.ecommerce.entities.Package;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCouponDto implements Serializable {

    private Long id;
    private String code;
    private String description;
    private Double discount;
    private DiscountCouponType discountCouponType;
    private String active;
    private String packageName;
    private String ProductName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long packageId;
    private Long productId;
    private LocalDateTime dateCreation;
    private Long userCreation;
    private LocalDateTime dateUpdate;
    private Long userUpdate;
    private ProductDto productDtoD;
    private PackageDto aPackageDto;


    public DiscountCouponDto(
            Long id, String code, String description, Double discount, DiscountCouponType discountCouponType,
            String active, LocalDateTime startDate, LocalDateTime endDate, Long packageId,
            Long productId, LocalDateTime dateCreation, Long userCreation,
            LocalDateTime dateUpdate, Long userUpdate,String packageName,String ProductName) {
        this.id = id;
        this.code = code;
        this.packageName=packageName;
        this.ProductName=ProductName;
        this.description = description;
        this.discount = discount;
        this.discountCouponType = discountCouponType;
        this.active = active;
        this.startDate = startDate;
        this.endDate = endDate;
        this.packageId = packageId;
        this.productId = productId;
        this.dateCreation = dateCreation;
        this.userCreation = userCreation;
        this.dateUpdate = dateUpdate;
        this.userUpdate = userUpdate;
    }
}
