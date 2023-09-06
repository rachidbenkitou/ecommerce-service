package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.Criteria.DiscountCouponCriteria;
import com.benkitoumiraouycoders.ecommerce.dtos.DiscountCouponDto;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;

import java.util.List;

public interface DiscountCoupanService {
    public ResponseDto deleteDiscountCouponsById(Long id) throws EntityNotFoundException ;
    public DiscountCouponDto updateDiscountCoupons(Long id, DiscountCouponDto discountCouponDto) throws EntityNotFoundException  ;
    public DiscountCouponDto persistDiscountCoupons(DiscountCouponDto discountCouponDto) throws EntityNotFoundException ;
    public DiscountCouponDto findDiscountCouponsById(Long id) throws EntityNotFoundException;
    public List<DiscountCouponDto> findDiscountCouponsByCriteria(DiscountCouponCriteria DiscountCouponCriteria) throws EntityNotFoundException ;
    }
