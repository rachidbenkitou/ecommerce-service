package com.benkitoucoders.myecommerce.exceptions.category;

import com.benkitoucoders.myecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends ApiBasedException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }

}
