package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.CategoryDto;

import java.util.List;

public interface CategoryServiceInter {
    List<CategoryDto> getCategoriesByQuery(Long id, String name);

    CategoryDto getCategoryById(Long id);

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto);

    void deleteCategoryById(Long id);

}
