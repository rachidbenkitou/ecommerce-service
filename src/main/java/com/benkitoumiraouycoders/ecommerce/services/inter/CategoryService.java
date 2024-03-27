package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.CategoryDto;
import com.benkitoumiraouycoders.ecommerce.dtos.ResponseDto;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategoriesByQuery(Long id, String name, String visbility);

    CategoryDto getCategoryById(Long id);

    CategoryDto addCategory(CategoryDto categoryDto) throws IOException;

    CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws IOException;

    ResponseDto deleteCategoryById(Long id);

}
