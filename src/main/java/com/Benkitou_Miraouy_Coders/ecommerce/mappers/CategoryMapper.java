package com.Benkitou_Miraouy_Coders.ecommerce.mappers;

import com.Benkitou_Miraouy_Coders.ecommerce.entities.Category;
import com.Benkitou_Miraouy_Coders.ecommerce.dtos.CategoryDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface CategoryMapper {
    CategoryDto modelToDto(Category category);
    List<CategoryDto> modelsToDtos(List<Category> categoryList);
    Category dtoToModel(CategoryDto categoryDto);
}
