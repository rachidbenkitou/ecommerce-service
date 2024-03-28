package com.benkitoucoders.ecommerce.exceptions;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends ApiBasedException {
    public EntityAlreadyExistsException(String messae) {
        super(messae);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
