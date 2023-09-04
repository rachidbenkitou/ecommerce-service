package com.benkitoumiraouycoders.ecommerce.controllers;

import com.benkitoumiraouycoders.ecommerce.dtos.ProductDto;
import com.benkitoumiraouycoders.ecommerce.services.inter.ProductServiceInter;
import com.benkitoumiraouycoders.ecommerce.services.strategy.inter.ImagesUploadStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:58213", allowCredentials = "true")
public class ProductController {

    private final ProductServiceInter productService;
    private final ImagesUploadStrategy productImagesStrategy;

    @Autowired
    public ProductController(ProductServiceInter productService, @Qualifier("productImageUploadStrategy") ImagesUploadStrategy productImagesStrategy) {
        this.productService = productService;
        this.productImagesStrategy = productImagesStrategy;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProductsByQuery(@RequestParam(name = "productId", required = false) Long id,
                                                               @RequestParam(name = "productName", required = false) String name,
                                                               @RequestParam(name = "productPrice", required = false) Double price,
                                                               @RequestParam(name = "productQuantity", required = false) Integer quantity,
                                                               @RequestParam(name = "productVisibility", required = false) String productVisibility,
                                                               @RequestParam(name = "categoryId", required = false) Long categoryId
    ) {

        return ResponseEntity.ok().body(productService.getProductsByQuery(id, name, price, quantity, productVisibility, categoryId));
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
    public ResponseEntity<?> deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/{productId}/uploadImages")
    public ResponseEntity<?> uploadImages(
            @RequestParam(name = "images", required = true) List<MultipartFile> images,
            @PathVariable(name = "productId", required = true) Long productId) throws IOException {
        productImagesStrategy.uploadImages(images, productId);
        return ResponseEntity.ok(null);
    }

}
