package com.benkitoucoders.myecommerce.daos;

import com.benkitoucoders.myecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    List<Product> findProductsBySubCategoryName(String subCategoryName);
    List<Product> findAllBySubCategoryCategoryName(String categoryName);
    List<Product> findAllByNameLikeIgnoreCase(String productName);
    List<Product> findAllByDateCreated(Date createdDate);
}
