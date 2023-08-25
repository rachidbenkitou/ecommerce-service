package com.benkitoucoders.ecommerce.dao;

import com.benkitoucoders.ecommerce.dtos.CategoryDto;
import com.benkitoucoders.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    @Query("SELECT NEW com.benkitoucoders.ecommerce.dtos.CategoryDto(c.id, c.name) " +
            "FROM Category c " +
            "WHERE (:categoryId IS NULL OR c.id = :categoryId) " +
            "AND (:categoryName IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :categoryName, '%')))")
    List<CategoryDto> findAllCategoryIdsAndNames(@Param("categoryId") Long categoryId, @Param("categoryName") String categoryName);

    boolean existsByName(String categoryName);
}
