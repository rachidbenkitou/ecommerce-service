package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.SupplierDto;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;

import java.io.IOException;
import java.util.List;

public interface SupplierService {
    List<SupplierDto> getSuppliers(Long supplierId, String name, String email, String phoneNumber);

    SupplierDto getSupplierById(Long id);

    SupplierDto addSupplier(SupplierDto supplierDto) throws IOException;

    SupplierDto updateSupplier(Long id, SupplierDto supplierDto);

    ResponseDto deleteSupplierById(Long id);
}
