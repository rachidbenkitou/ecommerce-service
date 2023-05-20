package com.benkitoucoders.myecommerce.services;

import com.benkitoucoders.myecommerce.dtos.CategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.CategoryResponseDto;
import com.benkitoucoders.myecommerce.entities.Category;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> getCategories() {
        return null;
    }

    @Override
    public CategoryResponseDto getCategoryByName(String categoryName) {
        return null;
    }

    @Override
    public CategoryResponseDto createNewCategory(CategoryRequestDto categoryRequestDto) {
        return null;
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto) {
        return null;
    }

    @Override
    public void deteteCategoryByName(String categoryName) {
        // This method is for deleting a category, it will configure later

    }
}
