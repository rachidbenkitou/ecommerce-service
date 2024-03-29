package com.benkitoucoders.ecommerce.services;
import com.benkitoucoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoucoders.ecommerce.services.inter.DiscountCoupanService;
import com.benkitoucoders.ecommerce.criteria.DiscountCouponCriteria;
import com.benkitoucoders.ecommerce.dao.DiscountCouponRepository;
import com.benkitoucoders.ecommerce.dtos.DiscountCouponDto;
import com.benkitoucoders.ecommerce.entities.DiscountCoupon;
import com.benkitoucoders.ecommerce.dtos.ResponseDto;
import com.benkitoucoders.ecommerce.mappers.DiscountCouponMapper;
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

    public List<DiscountCouponDto> findDiscountCouponsByCriteria(DiscountCouponCriteria DiscountCouponCriteria) throws EntityNotFoundException {
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
