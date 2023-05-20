package com.benkitoucoders.myecommerce.services.serviceinterfaces;

import com.benkitoucoders.myecommerce.dtos.ProductRequestDto;
import com.benkitoucoders.myecommerce.dtos.ProductResponseDto;
import com.benkitoucoders.myecommerce.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    ProductResponseDto getProductById(int productId);
    ProductResponseDto createNewProduct(ProductRequestDto productRequestDto);
    ProductResponseDto updateProduct(ProductRequestDto productRequestDto);
    void deteteProductById(int productId);
}
