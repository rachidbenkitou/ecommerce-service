package com.benkitoucoders.myecommerce.exceptions.customer;

import com.benkitoucoders.myecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends ApiBasedException {
    public CustomerNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
