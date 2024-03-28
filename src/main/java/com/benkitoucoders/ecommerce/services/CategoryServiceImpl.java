package com.benkitoucoders.ecommerce.services;

import com.benkitoucoders.ecommerce.exceptions.EntityAlreadyExistsException;
import com.benkitoucoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoucoders.ecommerce.services.inter.CategoryService;
import com.benkitoucoders.ecommerce.services.inter.ImageService;
import com.benkitoucoders.ecommerce.services.strategy.inter.ImageUploadStrategy;
import com.benkitoucoders.ecommerce.dao.CategoryDao;
import com.benkitoucoders.ecommerce.dao.ImageDao;
import com.benkitoucoders.ecommerce.dtos.CategoryDto;
import com.benkitoucoders.ecommerce.dtos.ResponseDto;
import com.benkitoucoders.ecommerce.mappers.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;
    private final ImageService imageService;
    private final ImageDao imageDao;
    @Qualifier("categoryImageUploadStrategy")
    private final ImageUploadStrategy imageServiceStrategy;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao, CategoryMapper categoryMapper
            , @Qualifier("categoryImageUploadStrategy") ImageUploadStrategy imageServiceStrategy
            , ImageService imageService, ImageDao imageDao) {
        this.categoryDao = categoryDao;
        this.categoryMapper = categoryMapper;
        this.imageServiceStrategy = imageServiceStrategy;
        this.imageService = imageService;
        this.imageDao = imageDao;
    }

    @Override
    public List<CategoryDto> getCategoriesByQuery(Long id, String name, String visbility) {
        return categoryDao.findAllCategoryIdsAndNames(id, name, visbility);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        CategoryDto categoryDto = getCategoriesByQuery(id, null, null).get(0);
        if (categoryDto != null) {
            return categoryDto;
        } else {
            throw new EntityNotFoundException(String.format("The category with the id %d is not found.", id));
        }
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) throws IOException {
        if (categoryDao.existsByName(categoryDto.getName())) {
            throw new EntityAlreadyExistsException(String.format("The category with the name %s is already exists", categoryDto.getName()));
        }
        categoryDto.setId(null);
        CategoryDto savedCategoryDto = categoryMapper.modelToDto(categoryDao.save(categoryMapper.dtoToModel(categoryDto)));
        if (savedCategoryDto != null) {
            imageServiceStrategy.uploadImage(categoryDto.getCategoryImage(), savedCategoryDto.getId());
            return savedCategoryDto;
        } else {
            throw new RuntimeException("Error while saving the product.");
        }
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws IOException {
        CategoryDto oldCategoryDto = getCategoryById(id);
        categoryDto.setId(oldCategoryDto.getId());
        if (categoryDto.getCategoryImage() != null) {
            imageService.deleteImageByFilePathFromLocalSystem(oldCategoryDto.getFilePath());
            imageDao.deleteByCategoryId(oldCategoryDto.getId());
            imageServiceStrategy.uploadImage(categoryDto.getCategoryImage(), categoryDto.getId());
        }
        return categoryMapper.modelToDto(categoryDao.save(categoryMapper.dtoToModel(categoryDto)));
    }

    @Override
    public ResponseDto deleteCategoryById(Long id) {
        CategoryDto categoryToDelete = getCategoryById(id);
        categoryDao.deleteById(id);
        imageService.deleteImageByFilePathFromLocalSystem(categoryToDelete.getFilePath());
        return ResponseDto.builder()
                .message("Category successfully deleted.")
                .build();
    }

}
