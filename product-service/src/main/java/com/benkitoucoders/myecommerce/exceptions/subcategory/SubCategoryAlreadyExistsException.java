package com.benkitoucoders.myecommerce.exceptions.subcategory;

import org.springframework.http.HttpStatus;

public class SubCategoryAlreadyExistsException extends RuntimeException {
    public SubCategoryAlreadyExistsException(String message) {
        super(message);
    }

}
