package com.benkitoucoders.myecommerce.controllers.api;

import com.benkitoucoders.myecommerce.dtos.subCategory.SubCategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.subCategory.SubCategoryResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subCategories")
@CrossOrigin("*")
public interface SubCategoryApi {
    @GetMapping
    ResponseEntity<List<SubCategoryResponseDto>> getSubCategories();

    @GetMapping("{subCategoryName}")
    ResponseEntity<SubCategoryResponseDto> getCategoryByName(@PathVariable String subCategoryName);

    @PostMapping
    ResponseEntity<SubCategoryResponseDto> addSubCategory(@RequestBody @Valid SubCategoryRequestDto subCategoryRequestDto);

    @PutMapping
    ResponseEntity<SubCategoryResponseDto> updateSubCategory(@RequestBody @Valid SubCategoryRequestDto subCategoryRequestDto);

    @DeleteMapping("{subCategoryName}")
    ResponseEntity<Void> deleteSubCategory(@PathVariable String subCategoryName);
}
