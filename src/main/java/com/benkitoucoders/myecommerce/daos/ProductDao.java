package com.benkitoucoders.myecommerce.daos;

import com.benkitoucoders.myecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {
}
