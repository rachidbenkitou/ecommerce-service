package com.benkitoucoders.myecommerce.controllers;

import com.benkitoucoders.myecommerce.controllers.api.CategoryApi;
import com.benkitoucoders.myecommerce.dtos.CategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.CategoryResponseDto;
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
    @Override
    public ResponseEntity<List<CategoryResponseDto>> getCategories(){
        List<CategoryResponseDto> categories = categoryService.getCategories();
        log.info("CategoryController::getCategories response {}", ObjectFormat.jsonAsString(categories));
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<CategoryResponseDto> getCategoryByName(@PathVariable String categoryName){
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryByName(categoryName);
        log.info("CategoryController::getCategory response {}", ObjectFormat.jsonAsString(categoryResponseDto));
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto){
        CategoryResponseDto  categoryResponseDto = categoryService.createNewCategory(categoryRequestDto);
        return processCategory(categoryResponseDto, "addCategory", HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<CategoryResponseDto> updateCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto){
        CategoryResponseDto  categoryResponseDto = categoryService.updateCategory(categoryRequestDto);
        return processCategory(categoryResponseDto, "updateCategory", HttpStatus.OK);
    }
    private ResponseEntity<CategoryResponseDto> processCategory(CategoryResponseDto categoryResponseDto, String categoryFunctionName, HttpStatus httpStatus){
        log.info(String.format("CategoryController::%s response {}", categoryFunctionName), ObjectFormat.jsonAsString(categoryResponseDto));
        return new ResponseEntity<>(categoryResponseDto, httpStatus);
    }
    @Override
    public ResponseEntity<Void> deleteCategory(@PathVariable int categoryId) {
        categoryService.deteteCategoryById(categoryId);
        log.info(String.format("CategoryController::CategoryController category %s deleted", categoryId));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
