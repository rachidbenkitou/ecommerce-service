package com.benkitoucoders.myecommerce.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObjectFormat {
    private ObjectFormat(){
        throw new IllegalStateException("Utility class cannot be instantiated");
    }
    public static String jsonAsString(Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Error occurred while serializing object to JSON", e);
        }
    }
}
