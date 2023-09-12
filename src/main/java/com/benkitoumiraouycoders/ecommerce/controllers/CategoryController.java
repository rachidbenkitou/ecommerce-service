
package com.benkitoumiraouycoders.ecommerce.controllers;

import com.benkitoumiraouycoders.ecommerce.dtos.CategoryDto;
import com.benkitoumiraouycoders.ecommerce.services.inter.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:58213", allowCredentials = "true")
public class CategoryController {

    private CategoryService categoryService;

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
    public ResponseEntity<CategoryDto> addCategory(
            @RequestBody CategoryDto categoryDto) throws IOException {
        return ResponseEntity.ok().body(categoryService.addCategory(categoryDto));
    }


    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok().body(categoryService.updateCategory(categoryId, categoryDto));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long categoryId) {

        return ResponseEntity.ok().body(categoryService.deleteCategoryById(categoryId));
    }


}


