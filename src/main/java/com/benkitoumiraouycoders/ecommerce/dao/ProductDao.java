package com.benkitoumiraouycoders.ecommerce.dao;

import com.benkitoumiraouycoders.ecommerce.dtos.ProductDto;
import com.benkitoumiraouycoders.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @Query(value = "select new com.benkitoumiraouycoders.ecommerce.dtos.ProductDto(" +
            " p.id,p.name, p.description, p.price, p.quantity, p.categoryId, c.name, p.dateCreated, p.dateUpdated) " +
            " FROM Product p " +
            " JOIN Category c ON p.categoryId = c.id " +
            " WHERE (:id IS NULL OR p.id = :id) " +
            "AND (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))" +
            " AND (:price IS NULL OR p.price = :price) " +
            " AND (:quantity IS NULL OR p.quantity = :quantity) " +
            " AND (:categoryId IS NULL OR p.categoryId = :categoryId) ")
    List<ProductDto> getProductsByQuery(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("price") Double price,
            @Param("quantity") Integer quantity,
            @Param("categoryId") Long categoryId
    );

    boolean existsByName(String name);
}
