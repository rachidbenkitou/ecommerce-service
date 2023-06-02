package com.benkitoucoders.myecommerce.services.serviceinterfaces;

import com.benkitoucoders.myecommerce.dtos.subCategory.SubCategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.subCategory.SubCategoryResponseDto;

import java.util.List;

public interface SubCategoryService {
    List<SubCategoryResponseDto> getSubCategories();

    SubCategoryResponseDto getSubCategoryByName(String subCategoryName);

    SubCategoryResponseDto createNewSubCategory(SubCategoryRequestDto subCategoryRequestDto);

    SubCategoryResponseDto updateSubCategory(SubCategoryRequestDto subCategoryRequestDto);

    void deteteSubCategoryByName(String subCategoryName);
}
