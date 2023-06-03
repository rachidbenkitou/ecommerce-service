package com.benkitoucoders.myecommerce.daos;

import com.benkitoucoders.myecommerce.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SubCategoryDao extends JpaRepository<SubCategory, Integer> {
    Optional<SubCategory> findSubCategoryByName(String categoryName);

    boolean existsByName(String categoryName);

    @Transactional
    void deleteByName(String subCategoryName);

}
