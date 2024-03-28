package com.benkitoucoders.ecommerce.services.inter;

import com.benkitoucoders.ecommerce.criteria.DiscountCouponCriteria;
import com.benkitoucoders.ecommerce.dtos.DiscountCouponDto;
import com.benkitoucoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoucoders.ecommerce.dtos.ResponseDto;

import java.util.List;

public interface DiscountCoupanService {
    public ResponseDto deleteDiscountCouponsById(Long id) throws EntityNotFoundException ;
    public DiscountCouponDto updateDiscountCoupons(Long id, DiscountCouponDto discountCouponDto) throws EntityNotFoundException  ;
    public DiscountCouponDto persistDiscountCoupons(DiscountCouponDto discountCouponDto) throws EntityNotFoundException ;
    public DiscountCouponDto findDiscountCouponsById(Long id) throws EntityNotFoundException;
    public List<DiscountCouponDto> findDiscountCouponsByCriteria(DiscountCouponCriteria DiscountCouponCriteria) throws EntityNotFoundException ;
    }
