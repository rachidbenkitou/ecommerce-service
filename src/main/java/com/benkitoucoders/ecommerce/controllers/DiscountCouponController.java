package com.benkitoucoders.ecommerce.controllers;

import com.benkitoucoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoucoders.ecommerce.services.inter.DiscountCoupanService;
import com.benkitoucoders.ecommerce.criteria.DiscountCouponCriteria;
import com.benkitoucoders.ecommerce.dtos.DiscountCouponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/discountCoupon")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:58213", allowCredentials = "true")
public class DiscountCouponController {

    private final DiscountCoupanService discountCouponService;
    
    @GetMapping
    public ResponseEntity<?> findVDiscountCouponsByCriteria(
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "active", required = false) String active,
            @RequestParam(name = "packageId", required = false) Long packageId,
            @RequestParam(name = "productId", required = false) Long productId) throws EntityNotFoundException {
        DiscountCouponCriteria discountCouponsCriteria = new DiscountCouponCriteria();
        discountCouponsCriteria.setCode(code);
        discountCouponsCriteria.setId(id);
        discountCouponsCriteria.setActive(active);
        discountCouponsCriteria.setPackageId(packageId);
        discountCouponsCriteria.setProductId(productId);
        return ResponseEntity.ok(  discountCouponService.findDiscountCouponsByCriteria(discountCouponsCriteria));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findVDiscountCouponsById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(  discountCouponService.findDiscountCouponsById(id));
    }

    @PostMapping
    public ResponseEntity<?> persistDiscountCoupons(@RequestBody DiscountCouponDto discountCouponsDto) throws EntityNotFoundException {
        return ResponseEntity.ok(  discountCouponService.persistDiscountCoupons(discountCouponsDto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateDiscountCoupons(@PathVariable Long id, @RequestBody DiscountCouponDto discountCouponsDto) throws EntityNotFoundException {
        return ResponseEntity.ok(  discountCouponService.updateDiscountCoupons(id, discountCouponsDto));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteDiscountCouponsById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(  discountCouponService.deleteDiscountCouponsById(id));
    }
}
