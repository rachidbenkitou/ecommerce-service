package com.benkitoucoders.ecommerce.daos;

import com.benkitoucoders.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
/*    @Query(value = "select new com.benkitoucoders.ecommerce.dtos.ProductDto(" +
            " p.id,p.name, p.description, p.price, p.quantity, c.name, p.dateCreated, p.dateUpdated) " +
            " FROM Product p " +
            " JOIN Category c ON p.categoryId = c.id " +
            " WHERE (:id IS NULL OR p.id = :id) " +
            "AND ((:name IS NULL OR p.name like :name OR p.name like :name)) " +
            " AND (:price IS NULL OR p.price = :price) " +
            " AND (:quantity IS NULL OR p.quantity = :quantity) " +
            " AND (:categoryId IS NULL OR p.categoryId = :categoryId) ")
    List<ProductDto> getProductsByQuery(
            @Param("id") Long id,
            @Param("name") String name
            , @Param("price") double price
            , @Param("quantity") int quantity
            , @Param("categoryId") Long categoryId
    );

    boolean existsByName(String name);*/
}
