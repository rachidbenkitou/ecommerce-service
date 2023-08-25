package com.Benkitou_Miraouy_Coders.ecommerce.exceptions;
import com.Benkitou_Miraouy_Coders.ecommerce.handlers.ApiBasedException;
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
