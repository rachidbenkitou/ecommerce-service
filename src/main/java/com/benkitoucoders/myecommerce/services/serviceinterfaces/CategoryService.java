package com.benkitoucoders.myecommerce.services.serviceinterfaces;

import com.benkitoucoders.myecommerce.dtos.CategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getCategories();
    CategoryResponseDto getCategoryByName(String categoryName);
    CategoryResponseDto createNewCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto);
    void deteteCategoryById(int categoryId);
}
