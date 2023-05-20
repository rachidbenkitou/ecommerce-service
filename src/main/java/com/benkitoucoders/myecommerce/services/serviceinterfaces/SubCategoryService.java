package com.benkitoucoders.myecommerce.services.serviceinterfaces;

import com.benkitoucoders.myecommerce.dtos.SubCategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.SubCategoryResponseDto;
import com.benkitoucoders.myecommerce.entities.SubCategory;

import java.util.List;

public interface SubCategoryService {
    List<SubCategory> getSubCategories();
    SubCategoryResponseDto getSubCategoryByName(String subCategoryName);
    SubCategoryResponseDto createNewSubCategory(SubCategoryRequestDto subCategoryRequestDto);
    SubCategoryResponseDto updateSubCategory(SubCategoryRequestDto subCategoryRequestDto);
    void deteteSubCategoryByName(String subCategoryName);
}
