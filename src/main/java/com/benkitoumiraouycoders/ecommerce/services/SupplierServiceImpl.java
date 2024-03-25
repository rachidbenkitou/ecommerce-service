package com.benkitoumiraouycoders.ecommerce.services;

import com.benkitoumiraouycoders.ecommerce.dtos.SupplierDto;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.services.inter.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    @Override
    public List<SupplierDto> getSuppliers(Long supplierId, String name, String email, String phoneNumber, Pageable pageable) {
        return null;
    }

    @Override
    public SupplierDto getSupplierById(Long id) {
        return null;
    }

    @Override
    public SupplierDto addSupplier(SupplierDto supplierDto) throws IOException {
        return null;
    }

    @Override
    public SupplierDto updateSupplier(Long id, SupplierDto supplierDto) {
        return null;
    }

    @Override
    public ResponseDto deleteSupplierById(Long id) {
        return null;
    }
}
