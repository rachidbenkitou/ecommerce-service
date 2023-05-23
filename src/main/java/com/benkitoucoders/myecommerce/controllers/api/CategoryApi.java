package com.benkitoucoders.myecommerce.controllers.api;

import com.benkitoucoders.myecommerce.dtos.CategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.CategoryResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categories")
public interface CategoryApi {
    @GetMapping
    ResponseEntity<List<CategoryResponseDto>> getCategories();
    @GetMapping("{categoryName}")
    ResponseEntity<CategoryResponseDto> getCategoryByName(@PathVariable String categoryName);
    @PostMapping
    ResponseEntity<CategoryResponseDto> addCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto);
    @PutMapping
    ResponseEntity<CategoryResponseDto> updateCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto);
    @DeleteMapping("{categoryId}")
    ResponseEntity<Void> deleteCategory(@PathVariable int categoryId);
}
