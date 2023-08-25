package com.benkitoumiraouycoders.ecommerce.handlers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

// This class has all the attributes that we want to show when the exception is thrown
// In our case, if the exception is thrown, we want to show to the user the message that describe the cause of the exception
// url where exception is thrown and tmeStamp the date when the exception is thrown
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorDetails {
    private String message;
    private String url;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timeStamp;
}

