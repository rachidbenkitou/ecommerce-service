package com.benkitoucoders.myecommerce.mappers;

import com.benkitoucoders.myecommerce.dtos.CategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.CategoryResponseDto;
import com.benkitoucoders.myecommerce.entities.Category;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CategoryMapper {
    CategoryResponseDto modelToDto(Category category);
    List<CategoryResponseDto> modelToDtos(List<Category> categories);
    Category dtoToModule(CategoryRequestDto categoryRequestDto);
}
