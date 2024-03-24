package com.benkitoumiraouycoders.ecommerce.dao;

import com.benkitoumiraouycoders.ecommerce.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierDao extends JpaRepository<Supplier, Long> {
}
