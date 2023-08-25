package com.Benkitou_Miraouy_Coders.ecommerce.services.inter;

import com.Benkitou_Miraouy_Coders.ecommerce.dtos.ProductDto;

import java.util.List;

public interface ProuctServiceInter {

    List<ProductDto> getProductsByQuery(Long id, String name, Double price, Integer quantity, Long categoryId);

    ProductDto getProductById(Long id);

    ProductDto addProduct(ProductDto productDto);

    ProductDto updateProduct(Long id, ProductDto productDto);

    void deleteProductById(Long id);
}
