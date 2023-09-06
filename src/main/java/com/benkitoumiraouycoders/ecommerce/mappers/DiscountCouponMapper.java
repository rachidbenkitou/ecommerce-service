package com.benkitoumiraouycoders.ecommerce.mappers;

import com.benkitoumiraouycoders.ecommerce.dtos.DiscountCouponDto;
import com.benkitoumiraouycoders.ecommerce.dtos.PackageDto;
import com.benkitoumiraouycoders.ecommerce.entities.DiscountCoupon;
import com.benkitoumiraouycoders.ecommerce.entities.Package;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface DiscountCouponMapper {
    DiscountCouponDto modelToDto(DiscountCoupon source);

    List<DiscountCouponDto> modelsToDtos(List<DiscountCoupon> sourceList);

    DiscountCoupon dtoToModel(DiscountCouponDto imageDto);
}
