package com.benkitoucoders.ecommerce.controllers;

import com.benkitoucoders.ecommerce.dtos.ProductDto;
import com.benkitoucoders.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductDto>> getProductsByQuery(@RequestParam(name = "productId", required = false) Long id,
                                                               @RequestParam(name = "productName", required = false) String name,
                                                               @RequestParam(name = "productPrice", required = false) Double price,
                                                               @RequestParam(name = "productQuantity", required = false) Integer quantity,
                                                               @RequestParam(name = "categoryId", required = false) Long categoryId
    ) {

        return ResponseEntity.ok().body(productService.getProductsByQuery(id, name, price, quantity, categoryId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok().body(productService.getProductById(productId));
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok().body(productService.addProduct(productDto));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok().body(productService.updateProduct(productId, productDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok().body("The product has been deleted successfully.");
    }

}
