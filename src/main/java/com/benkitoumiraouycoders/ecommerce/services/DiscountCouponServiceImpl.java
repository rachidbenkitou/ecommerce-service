package com.benkitoumiraouycoders.ecommerce.services;
import com.benkitoumiraouycoders.ecommerce.Criteria.DiscountCouponCriteria;
import com.benkitoumiraouycoders.ecommerce.dao.DiscountCouponRepository;
import com.benkitoumiraouycoders.ecommerce.dtos.DiscountCouponDto;
import com.benkitoumiraouycoders.ecommerce.entities.DiscountCoupon;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.mappers.DiscountCouponMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.DiscountCoupanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountCouponServiceImpl implements DiscountCoupanService {

    @Autowired
    private DiscountCouponRepository discountCouponRepository;

    @Autowired
    private DiscountCouponMapper   discountCouponMapper;
    @Autowired
    private ProductServiceImpl productService;

    public List<DiscountCouponDto> findDiscountCouponsByCriteria(DiscountCouponCriteria DiscountCouponCriteria) throws EntityNotFoundException  {
    return discountCouponRepository.findByQuery(
            DiscountCouponCriteria.getCode(),DiscountCouponCriteria.getId(),
            DiscountCouponCriteria.getPackageId(),DiscountCouponCriteria.getProductId(),
            DiscountCouponCriteria.getActive());
    }

    public DiscountCouponDto findDiscountCouponsById(Long id) throws EntityNotFoundException
    {
        DiscountCouponCriteria DiscountCouponCriteria = new DiscountCouponCriteria();
        DiscountCouponCriteria.setId(id);
        List<DiscountCouponDto> DiscountCouponDtoList = findDiscountCouponsByCriteria(DiscountCouponCriteria);
        if (DiscountCouponDtoList != null && !DiscountCouponDtoList.isEmpty()) {
            return DiscountCouponDtoList.get(0);
        } else {
            throw new EntityNotFoundException("The coupon with the id "+id+ "  is not found.");
        }
    }


    public DiscountCouponDto persistDiscountCoupons(DiscountCouponDto discountCouponDto) throws EntityNotFoundException {
        DiscountCoupon save =     discountCouponMapper.dtoToModel(discountCouponDto);
        return       discountCouponMapper.modelToDto(discountCouponRepository.save(save));
    }

    public DiscountCouponDto updateDiscountCoupons(Long id, DiscountCouponDto discountCouponDto) throws EntityNotFoundException  {

        DiscountCouponDto discountCouponDto1 = findDiscountCouponsById(id);
        discountCouponDto1.setId(id);
        discountCouponDto1.setDateUpdate(LocalDateTime.now());
        return       discountCouponMapper.modelToDto(discountCouponRepository.save(discountCouponMapper.dtoToModel(discountCouponDto1)));
    }

    public ResponseDto deleteDiscountCouponsById(Long id) throws EntityNotFoundException {
        ResponseDto responseDto = new ResponseDto();
        DiscountCouponDto DiscountCouponDto = findDiscountCouponsById(id);
        //Product productCriteria=productCriteria.builder().discountCouponId(DiscountCouponDto.getId()).build();
       // List<productDto> productDtoList=productService.getProductsByQuery(productCriteria);
        // if(!productDtoList.isEmpty()){
          //  throw new EntityNotFoundException("cette réduction encore d'utilisation");
        //}
        discountCouponRepository.deleteById(id);
        responseDto.setMessage("élément bien supprimé");
        return responseDto;
    }

}
