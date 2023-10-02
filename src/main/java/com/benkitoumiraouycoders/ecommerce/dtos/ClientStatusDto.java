package com.benkitoumiraouycoders.ecommerce.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientStatusDto implements Serializable {
    private Long id;
    private String name;
    private String color;

    public ClientStatusDto(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}
