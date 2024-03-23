package com.benkitoumiraouycoders.ecommerce.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsellDto implements Serializable {
    private Long id;
    private String Title;
    private String Description;
    private String Bottom_Title;





}
