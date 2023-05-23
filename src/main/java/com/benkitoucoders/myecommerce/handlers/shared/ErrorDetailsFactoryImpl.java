package com.benkitoucoders.myecommerce.handlers.shared;

import org.springframework.stereotype.Service;


@Service
class ErrorDetailsFactoryImpl implements ErrorDetailsFactory {

    // Go to ErrorDetailsFactory Interface for an explanation of what this method is for
    @Override
    public ErrorDetails createErrorDetails(String message, String description) {
        return new ErrorDetails(message, description);

    }

}