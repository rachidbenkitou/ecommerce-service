package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.SupplierDto;
import com.benkitoumiraouycoders.ecommerce.dtos.ResponseDto;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface SupplierService {
    List<SupplierDto> getSuppliers(Long supplierId, String name, String email, String phoneNumber, Pageable pageable);

    SupplierDto getSupplierById(Long id);

    SupplierDto addSupplier(SupplierDto supplierDto) throws IOException;

    SupplierDto updateSupplier(Long id, SupplierDto supplierDto);

    ResponseDto deleteSupplierById(Long id);
}
