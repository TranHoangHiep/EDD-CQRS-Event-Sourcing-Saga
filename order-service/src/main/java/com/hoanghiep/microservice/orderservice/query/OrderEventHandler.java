package com.hoanghiep.microservice.orderservice.query;

import com.hoanghiep.microservice.orderservice.core.data.OrderEntity;
import com.hoanghiep.microservice.orderservice.core.data.repo.OrderRepository;
import com.hoanghiep.microservice.orderservice.core.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("order-group")
public class OrderEventHandler {

    private final OrderRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("Order event handler");
        OrderEntity entity = new OrderEntity();
        BeanUtils.copyProperties(event, entity);
        orderRepository.save(entity);
    }
}
