package com.benkitoucoders.myecommerce.services.serviceinterfaces;

import com.benkitoucoders.myecommerce.dtos.product.ProductRequestDto;
import com.benkitoucoders.myecommerce.dtos.product.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getProducts();
    ProductResponseDto getProductById(int productId);
    ProductResponseDto createNewProduct(ProductRequestDto productRequestDto);
    ProductResponseDto updateProduct(ProductRequestDto productRequestDto);
    void deteteProductById(int productId);
}
