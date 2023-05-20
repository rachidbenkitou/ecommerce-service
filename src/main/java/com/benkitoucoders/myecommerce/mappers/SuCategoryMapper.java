package com.benkitoucoders.myecommerce.mappers;

import com.benkitoucoders.myecommerce.dtos.SubCategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.SubCategoryResponseDto;
import com.benkitoucoders.myecommerce.entities.SubCategory;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SuCategoryMapper {
    SubCategoryResponseDto modelToDto(SubCategory subCategory);
    List<SubCategoryResponseDto> modelToDtos(List<SubCategory> subCategories);
    SubCategory dtoToModule(SubCategoryRequestDto subCategoryRequestDto);
}
