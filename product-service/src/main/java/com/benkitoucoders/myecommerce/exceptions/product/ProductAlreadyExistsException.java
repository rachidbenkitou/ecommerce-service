package com.benkitoucoders.myecommerce.exceptions.product;

import com.benkitoucoders.myecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends ApiBasedException {
    public ProductAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }

}
