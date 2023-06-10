package com.benkitoucoders.myecommerce.exceptions.guestorder;

import com.benkitoucoders.myecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class GuestOrderNotFoundException extends ApiBasedException {
    public GuestOrderNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
