package com.benkitoucoders.myecommerce.daos;

import com.benkitoucoders.myecommerce.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryDao extends JpaRepository<SubCategory, Integer> {
}
