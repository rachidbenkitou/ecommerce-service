package com.benkitoucoders.myecommerce.daos;

import com.benkitoucoders.myecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
}
