package com.benkitoucoders.myecommerce.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserRequestDto implements Serializable {

    private Long id;

    @NotBlank(message = "The  name should not be null or empty")
    @Size(min = 1, max = 30, message = "The  name must be between 1 and 30 characters")
    private String firstName;

    @NotBlank(message = "The  name should not be null or empty")
    @Size(min = 1, max = 30, message = "The  name must be between 1 and 30 characters")
    private String secondName;

    @Pattern(regexp = "^\\+212\\d{8}$", message = "Phone number must be in the format (+212)xxxxxxxx")
    private String phone;
    
    @NotBlank(message = "The  birthday date should not be null or empty")
    private Date birthDayDate;
}
