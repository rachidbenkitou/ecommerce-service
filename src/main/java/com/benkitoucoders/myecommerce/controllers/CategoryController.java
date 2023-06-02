package com.benkitoucoders.myecommerce.controllers;

import com.benkitoucoders.myecommerce.controllers.api.CategoryApi;
import com.benkitoucoders.myecommerce.daos.CategoryDao;
import com.benkitoucoders.myecommerce.dtos.category.CategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.category.CategoryResponseDto;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.CategoryService;
import com.benkitoucoders.myecommerce.util.ObjectFormat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController implements CategoryApi {
    private final CategoryService categoryService;
    private final CategoryDao categoryDao;

    @Override
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        List<CategoryResponseDto> categories = categoryService.getCategories();
        log.info("CategoryController::getCategories response {}", ObjectFormat.jsonAsString(categories));
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryResponseDto> getCategoryByName(@PathVariable String categoryName) {
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryByName(categoryName);
        log.info("CategoryController::getCategory response {}", ObjectFormat.jsonAsString(categoryResponseDto));
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.createNewCategory(categoryRequestDto);
        return processCategory(categoryResponseDto, "addCategory", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CategoryResponseDto> updateCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(categoryRequestDto);
        return processCategory(categoryResponseDto, "updateCategory", HttpStatus.OK);
    }

    /**
     * Process the category response and return a ResponseEntity with the specified HTTP status.
     * This function is used to avoid code duplication in the addCategory and updateCategory methods.
     * It takes the category response DTO, the name of the category function being processed, and the desired HTTP status as parameters.
     * The category response DTO is logged, and a new ResponseEntity is created with the DTO and the specified HTTP status.
     *
     * @param categoryResponseDto  The response DTO containing the category data.
     * @param categoryFunctionName The name of the category function being processed.
     * @param httpStatus           The HTTP status to be returned in the ResponseEntity.
     * @return A ResponseEntity containing the category response DTO and the specified HTTP status.
     */
    private ResponseEntity<CategoryResponseDto> processCategory(CategoryResponseDto categoryResponseDto, String categoryFunctionName, HttpStatus httpStatus) {
        log.info(String.format("CategoryController::%s response {}", categoryFunctionName), ObjectFormat.jsonAsString(categoryResponseDto));
        return new ResponseEntity<>(categoryResponseDto, httpStatus);
    }

    @Override
    public ResponseEntity<Void> deleteCategoryByName(@PathVariable String categoryName) {
        categoryService.deteteCategoryByName(categoryName);
        log.info(String.format("CategoryController::CategoryController category %s deleted", categoryName));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
