package com.benkitoucoders.myecommerce.exceptions.address;

import com.benkitoucoders.myecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class AddressAlreadyExistsException extends ApiBasedException {
    public AddressAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
