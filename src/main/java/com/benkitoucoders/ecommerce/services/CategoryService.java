package com.benkitoucoders.ecommerce.services;

import com.benkitoucoders.ecommerce.daos.CategoryDao;
import com.benkitoucoders.ecommerce.dtos.CategoryDto;
import com.benkitoucoders.ecommerce.entities.Category;
import com.benkitoucoders.ecommerce.exceptions.EntityAlreadyExistsException;
import com.benkitoucoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoucoders.ecommerce.mappers.CategoryMapper;
import com.benkitoucoders.ecommerce.services.inter.CategoryServiceInter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceInter {
    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;

/*    @Override
    public List<CategoryDto> getCategoriesByQuery(Long id, String name) {
        List<CategoryDto> categoryDtoList = categoryDao.findAllCategoryIdsAndNames(id, name);
        return Optional.ofNullable(categoryDtoList)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ListIsEmptyException("No category found."));
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        List<CategoryDto> categoryDtoList = categoryDao.findAllCategoryIdsAndNames(id, null);
        return categoryDtoList.stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("The category with the id %d is not found.", id)));
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
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
    public void deleteCategoryById(Long id) {

        getCategoryById(id);
        categoryDao.deleteById(id);

    }*/

}
