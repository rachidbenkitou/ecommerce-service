package com.benkitoumiraouycoders.ecommerce.mappers;

import com.benkitoumiraouycoders.ecommerce.dtos.CategoryDto;
import com.benkitoumiraouycoders.ecommerce.entities.Category;
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
