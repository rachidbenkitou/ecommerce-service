package com.benkitoucoders.myecommerce.controllers.api;

import com.benkitoucoders.myecommerce.dtos.product.ProductRequestDto;
import com.benkitoucoders.myecommerce.dtos.product.ProductResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/products")
@CrossOrigin("*")
public interface ProductApi {
    @GetMapping
    ResponseEntity<List<ProductResponseDto>> getProducts();

    @GetMapping("/name/{productName}")
    ResponseEntity<List<ProductResponseDto>> getProductsByName(@PathVariable("productName") String productName);

    @GetMapping("/subCategory/{subCategoryName}")
    ResponseEntity<List<ProductResponseDto>> getProductsBySubCategoryName(@PathVariable("subCategoryName") String subCategoryName);

    @GetMapping("/category/{categoryName}")
    ResponseEntity<List<ProductResponseDto>> getProductsByCategoryName(@PathVariable("categoryName") String categoryName);

    @GetMapping("/createdDate/{createdDate}")
    ResponseEntity<List<ProductResponseDto>> getProductsByCreatedDate(@PathVariable("createdDate") Date createdDate);

    @GetMapping("{productId}")
    ResponseEntity<ProductResponseDto> getProductByName(@PathVariable int productId);

    @PostMapping
    ResponseEntity<ProductResponseDto> addProduct(@RequestBody @Valid ProductRequestDto productRequestDto);

    @PutMapping
    ResponseEntity<ProductResponseDto> updateProduct(@RequestBody @Valid ProductRequestDto productRequestDto);

    @DeleteMapping("{productId}")
    ResponseEntity<Void> deleteProduct(@PathVariable int productId);
}
