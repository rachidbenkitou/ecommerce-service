package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.ProductDto;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;

import java.io.IOException;
import java.util.List;

public interface ProuctService {

    List<ProductDto> getProductsByQuery(Long id, String name, Double price, Integer quantity, String visbility, Long categoryId);

    ProductDto getProductById(Long id);

    ProductDto addProduct(ProductDto productDto) throws IOException;

    ProductDto updateProduct(Long id, ProductDto productDto) throws IOException;

    ResponseDto deleteProductById(Long id);
}
