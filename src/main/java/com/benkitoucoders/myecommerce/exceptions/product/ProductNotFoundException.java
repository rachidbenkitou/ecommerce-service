package com.benkitoucoders.myecommerce.exceptions.product;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }

}
