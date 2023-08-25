package com.Benkitou_Miraouy_Coders.ecommerce.exceptions;

import com.Benkitou_Miraouy_Coders.ecommerce.handlers.ApiBasedException;
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
