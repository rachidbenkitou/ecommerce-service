package com.benkitoucoders.myecommerce.services;

import com.benkitoucoders.myecommerce.daos.CategoryDao;
import com.benkitoucoders.myecommerce.dtos.CategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.CategoryResponseDto;
import com.benkitoucoders.myecommerce.entities.Category;
import com.benkitoucoders.myecommerce.exceptions.CategoryAlreadyExistsException;
import com.benkitoucoders.myecommerce.exceptions.CategoryNotFoundException;
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

            log.debug("CategoryService:getCategories retrieving categories from database  {}", ObjectFormat.jsonAsString(categoryResponseDtos));

        } catch (Exception ex) {
            log.error("Exception occurred while retrieving categories from database , Exception message {}", ex.getMessage());
            throw new CategoryServiceBusinessException("Exception occurred while fetch all categories from Database");

        }

        log.info("CategoryService:getCategories execution ended.");
        return categoryResponseDtos;
    }

    @Override
    public CategoryResponseDto getCategoryByName(String categoryName) {
       CategoryResponseDto categoryResponseDto;
        try {
            log.info("CategoryService:getCategoryByName execution started.");

            Category category = categoryDao.findCategoryByName(categoryName)
                    .orElseThrow(() -> new CategoryNotFoundException(String.format("Category not found with name %s", categoryName)));
            categoryResponseDto = categoryMapper.modelToDto(category);

            log.debug("CategoryService:getCategoryByName retrieving category from database for id {} {}", categoryName, ObjectFormat.jsonAsString(categoryResponseDto));

        } catch (Exception ex) {
            log.error("Exception occurred while retrieving category {} from database , Exception message {}", categoryName, ex.getMessage());
            throw new CategoryServiceBusinessException("Exception occurred while fetch category from Database " + categoryName);
        }

        log.info("CategoryService:getCategoryByNam execution ended.");
        return categoryResponseDto;
    }

    @Override
    public CategoryResponseDto createNewCategory(CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto;

        try {
            log.info("CategoryService:createNewCategory execution started.");
            Category category = categoryMapper.dtoToModule(categoryRequestDto);
            log.debug("CategoryService:createNewCategory request parameters {}", ObjectFormat.jsonAsString(categoryRequestDto));

            if(categoryDao.existsByName(categoryRequestDto.getName()))
                throw new CategoryAlreadyExistsException(String.format("The category with name %s is already exists.", categoryRequestDto.getName()));

            Category categoryResults = categoryDao.save(category);
            categoryResponseDto = categoryMapper.modelToDto(categoryResults);
            log.debug("CategoryService:createNewCategory received response from Database {}", ObjectFormat.jsonAsString(categoryRequestDto));

        } catch (Exception ex) {
            log.error("Exception occurred while persisting category to database , Exception message {}", ex.getMessage());
            throw new CategoryServiceBusinessException("Exception occurred while create a new category");
        }
        log.info("CategoryService:createNewCategory execution ended.");
        return categoryResponseDto;
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto;

        try {
            log.info("CategoryService:updateCategory execution started.");
            Category category = categoryMapper.dtoToModule(categoryRequestDto);
            log.debug("CategoryService:updateCategory request parameters {}", ObjectFormat.jsonAsString(categoryRequestDto));

            if(categoryDao.existsByName(categoryRequestDto.getName()))
                throw new CategoryAlreadyExistsException(String.format("The category with name %s is already exists.", categoryRequestDto.getName()));

            Category categoryResults = categoryDao.save(category);
            categoryResponseDto = categoryMapper.modelToDto(categoryResults);
            log.debug("CategoryService:updateCategory received response from Database {}", ObjectFormat.jsonAsString(categoryRequestDto));

        } catch (Exception ex) {
            log.error("Exception occurred while persisting category {} to database , Exception message {}", categoryRequestDto.getName(), ex.getMessage());
            throw new CategoryServiceBusinessException("Exception occurred while update a  category");
        }
        log.info("CategoryService:updateCategory execution ended.");
        return categoryResponseDto;
    }

    @Override
    public void deteteCategoryByName(String categoryName) {
        try {
            log.info("CategoryService:deleteCategory execution started.");
            categoryDao.deleteCategoryByName(categoryName);
            log.debug("CategoryService:deleteCategory category is deleted from Database");

        }catch (Exception ex){
            log.error("Exception occurred while persisting category {} to database , Exception message {}", categoryName,ex.getMessage());
            throw new CategoryServiceBusinessException("Exception occurred while delete a  category");
        }
        log.info("CategoryService:deleteCategory execution ended.");
    }
}
