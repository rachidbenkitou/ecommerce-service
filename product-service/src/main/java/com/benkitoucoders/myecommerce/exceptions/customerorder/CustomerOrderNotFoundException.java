package com.benkitoucoders.myecommerce.exceptions.customerorder;

import com.benkitoucoders.myecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class CustomerOrderNotFoundException extends ApiBasedException {
    public CustomerOrderNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
