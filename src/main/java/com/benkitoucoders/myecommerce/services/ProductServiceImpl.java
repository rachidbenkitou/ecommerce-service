package com.benkitoucoders.myecommerce.services;

import com.benkitoucoders.myecommerce.dtos.ProductRequestDto;
import com.benkitoucoders.myecommerce.dtos.ProductResponseDto;
import com.benkitoucoders.myecommerce.entities.Product;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public ProductResponseDto getProductById(int productId) {
        return null;
    }

    @Override
    public ProductResponseDto createNewProduct(ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public void deteteProductById(int productId) {
        // This method is for deleting a product, it will configure later
    }
}
