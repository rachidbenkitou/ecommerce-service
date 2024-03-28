package com.benkitoucoders.ecommerce.dtos;

import lombok.Data;

@Data

public class OrderStatusDto {
    public OrderStatusDto() {
    }

    private Long id;

    private String name;
    private String color;

}
