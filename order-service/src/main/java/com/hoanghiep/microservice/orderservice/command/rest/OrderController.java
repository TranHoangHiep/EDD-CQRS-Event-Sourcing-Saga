package com.hoanghiep.microservice.orderservice.command.rest;

import com.hoanghiep.microservice.orderservice.command.CreateOrderCommand;
import com.hoanghiep.microservice.orderservice.command.rest.model.CreateOrderRequest;
import com.hoanghiep.microservice.orderservice.core.data.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        log.info("==========> Start create order controller <==========");
        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .productId(createOrderRequest.getProductId())
                .quantity(createOrderRequest.getQuantity())
                .addressId(createOrderRequest.getAddressId())
                .userId("27b95829-4f3f-4ddf-8983-151ba010e35b")
                .orderStatus(OrderStatus.CREATED)
                .build();
        String returnValue;
        try {
            returnValue  = commandGateway.sendAndWait(createOrderCommand);
        } catch (Exception exception) {
            returnValue = exception.getLocalizedMessage();
        }
        log.info("==========> End create order controller <==========");
        return returnValue;

    }
}
