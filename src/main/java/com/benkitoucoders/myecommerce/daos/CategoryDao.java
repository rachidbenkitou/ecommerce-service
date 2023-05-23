package com.benkitoucoders.myecommerce.daos;

import com.benkitoucoders.myecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryDao extends JpaRepository<Category, Integer> {
    Optional<Category> findCategoryByName(String categoryName);
    boolean existsByName(String categoryName);
    void deleteByName(String categoryName);
}
