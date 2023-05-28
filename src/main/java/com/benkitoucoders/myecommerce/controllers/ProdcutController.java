package com.benkitoucoders.myecommerce.controllers;

import com.benkitoucoders.myecommerce.controllers.api.ProductApi;
import com.benkitoucoders.myecommerce.dtos.ProductRequestDto;
import com.benkitoucoders.myecommerce.dtos.ProductResponseDto;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.ProductService;
import com.benkitoucoders.myecommerce.util.ObjectFormat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProdcutController implements ProductApi {
    private final ProductService productService;
    @Override
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        List<ProductResponseDto> categories = productService.getProducts();
        log.info("ProductController::getProducts response {}", ObjectFormat.jsonAsString(categories));
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponseDto> getProductByName(@PathVariable int productId){
        ProductResponseDto productResponseDto = productService.getProductById(productId);
        log.info("ProductController::getProduct response {}", ObjectFormat.jsonAsString(productResponseDto));
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody @Valid ProductRequestDto productRequestDto){
        ProductResponseDto  productResponseDto = productService.createNewProduct(productRequestDto);
        return processProduct(productResponseDto, "addProduct", HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody @Valid ProductRequestDto productRequestDto){
        ProductResponseDto  productResponseDto = productService.updateProduct(productRequestDto);
        return processProduct(productResponseDto, "updateProduct", HttpStatus.OK);
    }

    /**
     * Process the Product response and return a ResponseEntity with the specified HTTP status.
     * This function is used to avoid code duplication in the addProduct and updateProduct methods.
     * It takes the Product response DTO, the name of the Product function being processed, and the desired HTTP status as parameters.
     * The Product response DTO is logged, and a new ResponseEntity is created with the DTO and the specified HTTP status.
     *
     * @param productResponseDto  The response DTO containing the Product data.
     * @param productFunctionName The name of the Product function being processed.
     * @param httpStatus           The HTTP status to be returned in the ResponseEntity.
     * @return A ResponseEntity containing the Product response DTO and the specified HTTP status.
     */
    private ResponseEntity<ProductResponseDto> processProduct(ProductResponseDto productResponseDto, String productFunctionName, HttpStatus httpStatus){
        log.info(String.format("ProductController::%s response {}", productFunctionName), ObjectFormat.jsonAsString(productResponseDto));
        return new ResponseEntity<>(productResponseDto, httpStatus);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        productService.deteteProductById(productId);
        log.info(String.format("ProductController::ProductController Product %s deleted", productId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
