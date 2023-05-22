package com.benkitoucoders.myecommerce.services;

import com.benkitoucoders.myecommerce.daos.CategoryDao;
import com.benkitoucoders.myecommerce.dtos.CategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.CategoryResponseDto;
import com.benkitoucoders.myecommerce.entities.Category;
import com.benkitoucoders.myecommerce.exceptions.CategoryServiceBusinessException;
import com.benkitoucoders.myecommerce.mappers.CategoryMapper;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.CategoryService;
import com.benkitoucoders.myecommerce.util.ObjectFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;
    @Override
    public List<CategoryResponseDto> getCategories() {
        List<CategoryResponseDto> categoryResponseDtos = null;

        try {
            log.info("CategoryService:getCategories execution started.");

            List<Category> categoryList = categoryDao.findAll();

            if (!categoryList.isEmpty()) {
                categoryResponseDtos = categoryList.stream()
                        .map(categoryMapper::modelToDto)
                        .toList();
            } else {
                categoryResponseDtos = Collections.emptyList();
            }

            log.info("CategoryService:getCategories retrieving categories from database  {}", ObjectFormat.jsonAsString(categoryResponseDtos));

        } catch (Exception ex) {
            log.error("Exception occurred while retrieving categories from database , Exception message {}", ex.getMessage());
            throw new CategoryServiceBusinessException("Exception occurred while fetch all categories from Database");

        }

        log.info("CategoryService:getCategories execution ended.");
        return categoryResponseDtos;
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
