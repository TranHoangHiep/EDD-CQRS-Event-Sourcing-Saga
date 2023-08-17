package com.hoanghiep.microservice.orderservice.core.event;

import com.hoanghiep.microservice.orderservice.core.data.OrderStatus;
import lombok.Data;

@Data
public class OrderCreatedEvent {

    private String orderId;
    private String userId;
    private String productId;
    private Integer quantity;
    private String addressId;
    private OrderStatus orderStatus;
}
