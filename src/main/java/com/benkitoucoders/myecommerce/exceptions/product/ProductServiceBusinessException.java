package com.benkitoucoders.myecommerce.exceptions.product;

import com.benkitoucoders.myecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class ProductServiceBusinessException extends ApiBasedException {
    public ProductServiceBusinessException(String s) {
        super(s);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
