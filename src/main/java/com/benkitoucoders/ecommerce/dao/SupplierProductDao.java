package com.benkitoucoders.ecommerce.dao;

import com.benkitoucoders.ecommerce.entities.SupplierProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierProductDao extends JpaRepository<SupplierProduct, Long> {
    List<SupplierProduct> findBySupplierId(Long supplierId);


}
