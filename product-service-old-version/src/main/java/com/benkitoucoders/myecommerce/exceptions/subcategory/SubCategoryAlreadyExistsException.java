package com.benkitoucoders.myecommerce.exceptions.subcategory;

import com.benkitoucoders.myecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class SubCategoryAlreadyExistsException extends ApiBasedException {
    public SubCategoryAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }

}
