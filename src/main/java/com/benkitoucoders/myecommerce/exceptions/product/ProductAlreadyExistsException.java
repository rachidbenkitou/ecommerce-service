package com.benkitoucoders.myecommerce.exceptions.product;

import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String message) {
        super(message);
    }

}
