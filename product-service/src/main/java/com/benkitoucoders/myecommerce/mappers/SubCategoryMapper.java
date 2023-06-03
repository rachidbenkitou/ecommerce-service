package com.benkitoucoders.myecommerce.mappers;

import com.benkitoucoders.myecommerce.dtos.subCategory.SubCategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.subCategory.SubCategoryResponseDto;
import com.benkitoucoders.myecommerce.entities.SubCategory;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Service
public interface SubCategoryMapper {
    SubCategoryResponseDto modelToDto(SubCategory subCategory);
    List<SubCategoryResponseDto> modelToDtos(List<SubCategory> subCategories);
    SubCategory dtoToModule(SubCategoryRequestDto subCategoryRequestDto);
}
