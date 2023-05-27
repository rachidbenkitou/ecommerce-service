package com.benkitoucoders.myecommerce.exceptions.subcategory;

import com.benkitoucoders.myecommerce.handlers.ApiBasedException;
import org.springframework.http.HttpStatus;

public class SubCategoryServiceBusinessException extends ApiBasedException {
    public SubCategoryServiceBusinessException(String s) {
        super(s);

    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
