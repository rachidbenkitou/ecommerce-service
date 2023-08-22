package com.benkitoucoders.ecommerce.controllers;

import com.benkitoucoders.ecommerce.daos.ProductDao;
import com.benkitoucoders.ecommerce.dtos.ProductDto;
import com.benkitoucoders.ecommerce.mappers.ProductMapper;
import com.benkitoucoders.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    /*
    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping("/productByQuery")
    public ResponseEntity<List<ProductDto>> getProductsByQuery(@RequestParam(name = "id", required = false) Long id,
                                                               @RequestParam(name = "name", required = false) String name,
                                                               @RequestParam(name = "price", required = false) double price,
                                                               @RequestParam(name = "quantity", required = false) int quantity,
                                                               @RequestParam(name = "categoryId", required = false) Long categoryId
                                                               ) {

        return ResponseEntity.ok().body(productService.getProductsByQuery(id,name,price,quantity,categoryId ));
    }

     */

}
