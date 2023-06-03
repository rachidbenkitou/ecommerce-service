package com.benkitoucoders.myecommerce.controllers.api;

import com.benkitoucoders.myecommerce.dtos.product.ProductRequestDto;
import com.benkitoucoders.myecommerce.dtos.product.ProductResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
public interface ProductApi {
    @GetMapping
    ResponseEntity<List<ProductResponseDto>> getProducts();
    @GetMapping("{productId}")
    ResponseEntity<ProductResponseDto> getProductByName(@PathVariable int productId);
    @PostMapping
    ResponseEntity<ProductResponseDto> addProduct(@RequestBody @Valid ProductRequestDto productRequestDto);
    @PutMapping
    ResponseEntity<ProductResponseDto> updateProduct(@RequestBody @Valid ProductRequestDto productRequestDto);
    @DeleteMapping("{productId}")
    ResponseEntity<Void> deleteProduct(@PathVariable int productId);
}
