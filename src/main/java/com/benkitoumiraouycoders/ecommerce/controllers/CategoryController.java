
package com.benkitoumiraouycoders.ecommerce.controllers;

import com.benkitoumiraouycoders.ecommerce.dtos.CategoryDto;
import com.benkitoumiraouycoders.ecommerce.services.CategoryService;
import com.benkitoumiraouycoders.ecommerce.services.inter.CategoryServiceInter;
import com.benkitoumiraouycoders.ecommerce.services.strategy.CategoryImageUploadStrategy;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:58213", allowCredentials = "true")
public class CategoryController {

    private   CategoryServiceInter categoryService;
    private  CategoryImageUploadStrategy categoryImageUploadStrategy;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategoriesByQuery(@RequestParam(name = "categoryId", required = false) Long categoryId,
                                                                  @RequestParam(name = "categoryName", required = false) String categoryName,
                                                                  @RequestParam(name = "categoryVisbility", required = false) String categoryVisbility
    ) {
        return ResponseEntity.ok().body(categoryService.getCategoriesByQuery(categoryId, categoryName, categoryVisbility));
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
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadCategoryImage(
            @RequestParam(name = "image", required = true) MultipartFile image,
            @RequestParam(name = "categoryId", required = true) Long categoryId) throws IOException {
        categoryImageUploadStrategy.uploadImage(image, categoryId);
        return ResponseEntity.ok(null);
    }
}


