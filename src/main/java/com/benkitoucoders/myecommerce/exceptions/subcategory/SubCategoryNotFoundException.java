package com.benkitoucoders.myecommerce.exceptions.subcategory;

import org.springframework.http.HttpStatus;

public class SubCategoryNotFoundException extends RuntimeException {
    public SubCategoryNotFoundException(String message) {
        super(message);

    }

}
