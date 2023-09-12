package com.benkitoumiraouycoders.ecommerce.services;

import com.benkitoumiraouycoders.ecommerce.dao.CategoryDao;
import com.benkitoumiraouycoders.ecommerce.dtos.CategoryDto;
import com.benkitoumiraouycoders.ecommerce.dtos.ImageDto;
import com.benkitoumiraouycoders.ecommerce.entities.Category;
import com.benkitoumiraouycoders.ecommerce.entities.Image;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityAlreadyExistsException;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.mappers.CategoryMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements com.benkitoumiraouycoders.ecommerce.services.inter.CategoryService {
    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;
    private final ImageService imageService;

    @Override
    public List<CategoryDto> getCategoriesByQuery(Long id, String name, String visbility) {
        List<CategoryDto> categoryDtoList=categoryDao.findAllCategoryIdsAndNames(id, name, visbility);
        for(CategoryDto categoryDto : categoryDtoList){
            categoryDto.setCategoryImageUrl(imageService.getImagesFromAws(categoryDto.getCategoryImagePath()));
        }
        return categoryDtoList;
    }
    @Override
    public CategoryDto getCategoryById(Long id) {
        Optional<Category> category = categoryDao.findById(id);

        if (category.isPresent()) {
            return categoryMapper.modelToDto(category.get());

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

        return categoryMapper.modelToDto(categoryDao.save(categoryMapper.dtoToModel(categoryDto)));
    }
    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        CategoryDto oldCategoryDto = getCategoryById(id);
        categoryDto.setId(oldCategoryDto.getId());
        Category updatedCategory = categoryDao.save(categoryMapper.dtoToModel(categoryDto));
        return categoryMapper.modelToDto(updatedCategory);
    }
    @Override
    public ResponseDto deleteCategoryById(Long id) {
        getCategoryById(id);
        categoryDao.deleteById(id);
        return ResponseDto.builder()
                .message("Category successfully deleted.")
                .build();
    }

}
