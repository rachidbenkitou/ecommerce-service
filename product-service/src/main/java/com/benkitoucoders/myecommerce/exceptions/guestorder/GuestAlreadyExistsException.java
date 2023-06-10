package com.benkitoucoders.myecommerce.exceptions.guestorder;

import com.benkitoucoders.myecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class GuestAlreadyExistsException extends ApiBasedException {
    public GuestAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
