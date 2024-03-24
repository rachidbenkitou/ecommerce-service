package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.SupplierProductDto;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;

import java.io.IOException;
import java.util.List;

public interface SupplierProductService {
    List<SupplierProductDto> getAllSupplierProduct();

    SupplierProductDto getSupplierProductById(Long id);
    SupplierProductDto getSupplierProductBySupplierId(Long supplierId);

    SupplierProductDto addSupplierProduct(SupplierProductDto supplierProductDto) throws IOException;

    SupplierProductDto updateSupplierProduct(Long id, SupplierProductDto supplierProductDto);

    ResponseDto deleteSupplierProductById(Long id);
}
