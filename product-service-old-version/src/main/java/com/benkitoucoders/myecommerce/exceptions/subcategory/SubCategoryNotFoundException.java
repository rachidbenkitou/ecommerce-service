package com.benkitoucoders.myecommerce.exceptions.subcategory;

import com.benkitoucoders.myecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class SubCategoryNotFoundException extends ApiBasedException {
    public SubCategoryNotFoundException(String message) {
        super(message);

    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }

}
