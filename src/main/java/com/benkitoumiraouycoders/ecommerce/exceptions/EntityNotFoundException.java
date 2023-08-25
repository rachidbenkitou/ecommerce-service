package com.benkitoumiraouycoders.ecommerce.exceptions;
import com.benkitoumiraouycoders.ecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ApiBasedException {
    public EntityNotFoundException(String message) {
        super(message);
    }
    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
