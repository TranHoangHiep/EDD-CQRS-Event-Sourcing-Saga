package com.hoanghiep.microservice.orderservice.command.rest.model;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private String productId;
    private Integer quantity;
    private String addressId;
}
