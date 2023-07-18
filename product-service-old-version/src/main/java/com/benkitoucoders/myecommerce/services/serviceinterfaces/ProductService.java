package com.benkitoucoders.myecommerce.services.serviceinterfaces;

import com.benkitoucoders.myecommerce.dtos.product.ProductRequestDto;
import com.benkitoucoders.myecommerce.dtos.product.ProductResponseDto;

import java.util.Date;
import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getProducts();

    List<ProductResponseDto> getProductsBySubCategoryName(String subCategoryName);

    List<ProductResponseDto> getProductsByCategoryName(String categoryName);

    List<ProductResponseDto> getProductsByName(String productName);

    List<ProductResponseDto> getProductsByCreatedDate(Date productCreatedDate);

    ProductResponseDto getProductById(int productId);
    ProductResponseDto createNewProduct(ProductRequestDto productRequestDto);
    ProductResponseDto updateProduct(ProductRequestDto productRequestDto);
    void deteteProductById(int productId);
}
