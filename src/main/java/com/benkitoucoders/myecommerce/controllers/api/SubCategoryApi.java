package com.benkitoucoders.myecommerce.controllers.api;

import com.benkitoucoders.myecommerce.dtos.SubCategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.SubCategoryResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subCategories")
public interface SubCategoryApi {
    @GetMapping
    ResponseEntity<List<SubCategoryResponseDto>> getSubCategories();
    @GetMapping("{subCategoryName}")
    ResponseEntity<SubCategoryResponseDto> getCategoryByName(@PathVariable String subCategoryName);
    @PostMapping
    ResponseEntity<SubCategoryResponseDto> addSubCategory(@RequestBody @Valid SubCategoryRequestDto subCategoryRequestDto);
    @PutMapping
    ResponseEntity<SubCategoryResponseDto> updateSubCategory(@RequestBody @Valid SubCategoryRequestDto subCategoryRequestDto);
    @DeleteMapping("{subCategoryId}")
    ResponseEntity<Void> deleteSubCategory(@PathVariable int subCategoryId);
}
