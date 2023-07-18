package com.benkitoucoders.myecommerce.exceptions.customer;

import com.benkitoucoders.myecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class CustomerAlreadyExistsException extends ApiBasedException {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
