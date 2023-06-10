package com.benkitoucoders.myecommerce.dtos.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class AddressRequestDto implements Serializable {

    private Long id;
    @NotBlank(message = "The city name should not be null or empty")
    @Size(min = 1, max = 20, message = "The city name must be between 1 and 20 characters")
    private String city;
    @NotBlank(message = "The city name should not be null or empty")
    @Size(min = 1, max = 100, message = "The city name must be between 1 and 100 characters")
    private String street;
}
