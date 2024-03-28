package com.benkitoucoders.ecommerce.services;

import com.benkitoucoders.ecommerce.dtos.ResponseDto;
import com.benkitoucoders.ecommerce.dtos.SupplierProductDto;
import com.benkitoucoders.ecommerce.services.inter.SupplierProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SupplierProductServiceImpl implements SupplierProductService {
    @Override
    public List<SupplierProductDto> getAllSupplierProduct() {
        return null;
    }

    @Override
    public SupplierProductDto getSupplierProductById(Long id) {
        return null;
    }

    @Override
    public SupplierProductDto getSupplierProductBySupplierId(Long supplierId) {
        return null;
    }

    @Override
    public SupplierProductDto addSupplierProduct(SupplierProductDto supplierProductDto) throws IOException {
        return null;
    }

    @Override
    public SupplierProductDto updateSupplierProduct(Long id, SupplierProductDto supplierProductDto) {
        return null;
    }

    @Override
    public ResponseDto deleteSupplierProductById(Long id) {
        return null;
    }
}
