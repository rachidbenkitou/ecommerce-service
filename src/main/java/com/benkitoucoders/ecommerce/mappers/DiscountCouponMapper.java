package com.benkitoucoders.ecommerce.mappers;

import com.benkitoucoders.ecommerce.entities.DiscountCoupon;
import com.benkitoucoders.ecommerce.dtos.DiscountCouponDto;
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
