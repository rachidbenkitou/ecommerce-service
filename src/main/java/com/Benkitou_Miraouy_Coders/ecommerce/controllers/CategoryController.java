
package com.Benkitou_Miraouy_Coders.ecommerce.controllers;

import com.Benkitou_Miraouy_Coders.ecommerce.dtos.CategoryDto;
import com.Benkitou_Miraouy_Coders.ecommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategoriesByQuery(@RequestParam(name = "categoryId", required = false) Long categoryId,
                                                                  @RequestParam(name = "categoryName", required = false) String categoryName) {
        return ResponseEntity.ok().body(categoryService.getCategoriesByQuery(categoryId, categoryName));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(categoryId));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok().body(categoryService.addCategory(categoryDto));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok().body(categoryService.updateCategory(categoryId, categoryDto));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok().body("The category has been deleted successfully.");
    }
}


