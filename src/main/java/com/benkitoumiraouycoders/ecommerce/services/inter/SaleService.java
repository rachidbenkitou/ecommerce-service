package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.Criteria.SaleCriteria;
import com.benkitoumiraouycoders.ecommerce.dtos.SaleDto;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;

import java.util.List;

public interface SaleService {
    public List<SaleDto> findsalesByCriteria(SaleCriteria saleCriteria) throws EntityNotFoundException ;
    public SaleDto findsalesById(Long id) throws EntityNotFoundException;
    public SaleDto persistsales(SaleDto saleDto) throws EntityNotFoundException ;
    public SaleDto updatesales(Long id, SaleDto saleDto) throws EntityNotFoundException  ;
    public ResponseDto deletesalesById(Long id) throws EntityNotFoundException ;
    }
