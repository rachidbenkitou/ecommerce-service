package com.benkitoucoders.myecommerce.exceptions.category;

import com.benkitoucoders.myecommerce.handlers.shared.ApiBasedException;
import org.springframework.http.HttpStatus;

public class CategoryServiceBusinessException extends ApiBasedException {
    public CategoryServiceBusinessException(String s) {
        super(s);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
