package com.benkitoucoders.myecommerce.services;

import com.benkitoucoders.myecommerce.daos.CategoryDao;
import com.benkitoucoders.myecommerce.dtos.CategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.CategoryResponseDto;
import com.benkitoucoders.myecommerce.entities.Category;
import com.benkitoucoders.myecommerce.exceptions.category.CategoryAlreadyExistsException;
import com.benkitoucoders.myecommerce.exceptions.category.CategoryNotFoundException;
import com.benkitoucoders.myecommerce.exceptions.category.CategoryServiceBusinessException;
import com.benkitoucoders.myecommerce.mappers.CategoryMapper;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.CategoryService;
import com.benkitoucoders.myecommerce.util.ObjectFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Profile(value = {"local", "dev", "prod"})
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
            throw new CategoryServiceBusinessException("Exception occurred while fetching category from Database " );
        }
        log.info("CategoryService:getCategoryByNam execution ended.");
        return categoryResponseDto;
    }


    @Override
    public CategoryResponseDto createNewCategory(CategoryRequestDto categoryRequestDto) {
        return processCategory(categoryRequestDto, "createNewCategory");
    }


    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto) {
        return processCategory(categoryRequestDto, "updateCategory");
    }



    /**
     * Processes the category based on the provided category request data and the category function name.
     *
     * @param categoryRequestDto    The CategoryRequestDto object containing the category data.
     * @param categoryFunctionName  The name of the category function being executed ("createNewCategory" or "updateCategory").
     *                              This parameter is used to differentiate between creating and updating categories.
     * @return The CategoryResponseDto object representing the processed category.
     * @throws CategoryAlreadyExistsException   If the category with the same name already exists (applicable for createNewCategory() only).
     * @throws CategoryServiceBusinessException If an exception occurs while processing the category.
     */
    /*
    To avoid code duplication and handle logs appropriately, a shared function called processCategory()
     is implemented for creating and updating categories. This function takes the categoryFunctionName as
      a parameter to determine the specific operation being performed. When calling the function, you need
       to provide the appropriate function name: "createNewCategory" for creating a new category and "updateCategory" for updating an existing category.

     By using the categoryFunctionName parameter, the function can dynamically generate log messages
     to indicate whether a category was created or updated. This helps in identifying the state of the operation
     being executed. When creating a category, the log message will indicate "created", and when updating a category,
     the log message will indicate "updated".
     */
    private CategoryResponseDto processCategory(CategoryRequestDto categoryRequestDto, String categoryFunctionName){
        CategoryResponseDto categoryResponseDto;

        try {
            log.info(String.format("CategoryService:%s execution started.", categoryFunctionName));
            Category category = categoryMapper.dtoToModule(categoryRequestDto);
            log.debug(String.format("CategoryService:%s request parameters {}", categoryFunctionName), ObjectFormat.jsonAsString(categoryRequestDto));

            /*
            To ensure the category's existence, we perform a verification step when creating a category.
            This verification is not necessary when updating a category since we already know it exists.
             */
            if(categoryFunctionName.equals("createNewCategory") && categoryDao.existsByName(categoryRequestDto.getName()))
                throw new CategoryAlreadyExistsException(String.format("The category with name %s is already exists.", categoryRequestDto.getName()));
            Category categoryResults = categoryDao.save(category);
            categoryResponseDto = categoryMapper.modelToDto(categoryResults);
            log.debug(String.format("CategoryService:%s received response from Database {}", categoryFunctionName), ObjectFormat.jsonAsString(categoryRequestDto));
        } catch (Exception ex) {
            log.error("Exception occurred while persisting category to database , Exception message {}", ex.getMessage());
            throw new CategoryServiceBusinessException(String.format("Exception occurred while %s", categoryFunctionName));
        }
        log.info(String.format("CategoryService:%s execution ended.", categoryFunctionName));
        return categoryResponseDto;
    }


    @Override
    public void deteteCategoryById(int categoryId) {
        try {
            log.info("CategoryService:deleteCategory execution started.");
            categoryDao.deleteById(categoryId);
            log.debug("CategoryService:deleteCategory category is deleted from the Database");
        } catch (Exception ex) {
            log.error("Exception occurred while deleting category {} from the database. Exception message: {}", categoryId, ex.getMessage());
            throw new CategoryServiceBusinessException("Exception occurred while deleting a category");
        }
        log.info("CategoryService:deleteCategory execution ended.");
    }
}

