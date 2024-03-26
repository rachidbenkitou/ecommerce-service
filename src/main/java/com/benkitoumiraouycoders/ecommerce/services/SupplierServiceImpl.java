package com.benkitoumiraouycoders.ecommerce.services;

import com.benkitoumiraouycoders.ecommerce.dao.SupplierDao;
import com.benkitoumiraouycoders.ecommerce.dtos.SupplierDto;
import com.benkitoumiraouycoders.ecommerce.entities.Supplier;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.mappers.SupplierMapper;
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
    private final SupplierDao supplierDao;
    private final SupplierMapper supplierMapper;

    @Override
    public List<SupplierDto> getSuppliers(Long supplierId, String name, String email, String phoneNumber, Pageable pageable) {
        try {
            // Assuming there's a method in SupplierDao to find suppliers with the given criteria
            List<Supplier> suppliers = supplierDao.findByCriteria(supplierId, name, email, phoneNumber, pageable);
            return supplierMapper.modelsToDtos(suppliers);
        } catch (Exception e) {
            log.error("Error retrieving suppliers", e);
            throw new RuntimeException("Error retrieving suppliers", e);
        }
    }

    @Override
    public SupplierDto getSupplierById(Long id) {
        log.debug("Fetching supplier with id : {}",id);
        return supplierDao.findById(id)
                .map(supplier -> {
                    log.info("supplier found with id: {}",id);
                    return new SupplierDto(supplier.getId(),supplier.getName(),supplier.getEmail(),supplier.getAddress(),supplier.getPhoneNumber(),supplier.getDateCreation());
                })
                .orElseThrow(() -> {
            log.error("supplier not found with id:{}",id);
            return new EntityNotFoundException(String.format("the supplier with the id %d is not found",id));
        });
    }

    @Override
    public SupplierDto addSupplier(SupplierDto supplierDto) throws IOException {
        try {
            Supplier supplier = supplierMapper.dtoToModel(supplierDto);
            Supplier savedSupplier = supplierDao.save(supplier);
            log.info("Added new supplier with id: {}", savedSupplier.getId());
            return supplierMapper.modelToDto(savedSupplier);
        } catch (Exception e) {
            log.error("Error adding new supplier", e);
            throw new RuntimeException("Error adding new supplier", e);
        }
    }

    @Override
    public SupplierDto updateSupplier(Long id, SupplierDto supplierDto)
    {
        return null;
    }

    @Override
    public ResponseDto deleteSupplierById(Long id)
    {
        return null;
    }
}
