package com.Benkitou_Miraouy_Coders.ecommerce.mappers;

import com.Benkitou_Miraouy_Coders.ecommerce.entities.Product;
import com.Benkitou_Miraouy_Coders.ecommerce.dtos.ProductDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface ProductMapper {
    ProductDto modelToDto(Product product);
    List<ProductDto> modelsToDtos(List<Product> productList);
    Product dtoToModel(ProductDto productDto);

}
