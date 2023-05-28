package com.benkitoucoders.myecommerce.exceptions.category;

import org.springframework.http.HttpStatus;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String message) {
        super(message);
    }

}
