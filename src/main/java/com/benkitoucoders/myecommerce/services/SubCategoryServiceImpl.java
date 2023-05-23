package com.benkitoucoders.myecommerce.services;

import com.benkitoucoders.myecommerce.dtos.SubCategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.SubCategoryResponseDto;
import com.benkitoucoders.myecommerce.entities.SubCategory;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.SubCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Profile(value = {"local", "dev", "prod"})
@RequiredArgsConstructor
@Slf4j
public class SubCategoryServiceImpl implements SubCategoryService {
    @Override
    public List<SubCategory> getSubCategories() {
        return null;
    }

    @Override
    public SubCategoryResponseDto getSubCategoryByName(String subCategoryName) {
        return null;
    }

    @Override
    public SubCategoryResponseDto createNewSubCategory(SubCategoryRequestDto subCategoryRequestDto) {
        return null;
    }

    @Override
    public SubCategoryResponseDto updateSubCategory(SubCategoryRequestDto subCategoryRequestDto) {
        return null;
    }

    @Override
    public void deteteSubCategoryByName(String subCategoryName) {
        // This method is for deleting a subCategory, it will configure later
    }
}
