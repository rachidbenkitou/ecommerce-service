package com.benkitoucoders.myecommerce.exceptions.customerorder;

import com.benkitoucoders.myecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class CustomerOrderAlreadyExistsException extends ApiBasedException {
    public CustomerOrderAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
