package com.benkitoucoders.myecommerce.mappers;

import com.benkitoucoders.myecommerce.dtos.product.ProductRequestDto;
import com.benkitoucoders.myecommerce.dtos.product.ProductResponseDto;
import com.benkitoucoders.myecommerce.entities.Product;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Service
public interface ProductMapper {
    ProductResponseDto modelToDto(Product product);
    List<ProductResponseDto> modelToDtos(List<Product> products);
    Product dtoToModule(ProductRequestDto productRequestDto);
}
