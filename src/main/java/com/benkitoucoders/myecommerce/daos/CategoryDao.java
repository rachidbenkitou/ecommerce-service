package com.benkitoucoders.myecommerce.daos;

import com.benkitoucoders.myecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CategoryDao extends JpaRepository<Category, Integer> {
    Optional<Category> findCategoryByName(String categoryName);
    boolean existsByName(String categoryName);
    @Transactional
    void deleteByName(String name);
}
