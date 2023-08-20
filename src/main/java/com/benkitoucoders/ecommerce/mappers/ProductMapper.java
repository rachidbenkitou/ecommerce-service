package com.benkitoucoders.ecommerce.mappers;

import com.benkitoucoders.ecommerce.dtos.ProductDto;
import com.benkitoucoders.ecommerce.entities.Product;
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
