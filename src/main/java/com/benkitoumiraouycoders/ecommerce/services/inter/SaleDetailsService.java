package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.criteria.SaleDetailsCriteria;
import com.benkitoumiraouycoders.ecommerce.dtos.SaleDetailsDto;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;

import java.util.List;

public interface SaleDetailsService {
    List<SaleDetailsDto> findSaleDetailsByCriteria(SaleDetailsCriteria saleDetailsCriteria) throws EntityNotFoundException;

    SaleDetailsDto findSaleDetailsById(Long id) throws EntityNotFoundException;

    SaleDetailsDto persistSaleDetails(SaleDetailsDto saleDetailsDto) throws EntityNotFoundException;

    SaleDetailsDto updatesaleDetails(Long id, SaleDetailsDto saleDetailsDto) throws EntityNotFoundException;

    ResponseDto deleteSaleDetailsById(Long id) throws EntityNotFoundException;
}
