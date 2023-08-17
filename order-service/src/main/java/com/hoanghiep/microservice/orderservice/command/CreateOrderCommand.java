package com.hoanghiep.microservice.orderservice.command;

import com.hoanghiep.microservice.orderservice.core.data.OrderStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
    private final String userId;
    private final String productId;
    private final Integer quantity;
    private final String addressId;
    private final OrderStatus orderStatus;
}
