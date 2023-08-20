package com.hoanghiep.microservice.orderservice.query;

import com.hoanghiep.microservice.orderservice.core.data.OrderEntity;
import com.hoanghiep.microservice.orderservice.core.data.repo.OrderRepository;
import com.hoanghiep.microservice.orderservice.query.rest.model.OrderRestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderQueryHandler {

    private final OrderRepository orderRepository;

    @QueryHandler
    public List<OrderRestModel> findOrders(FindOrderQuery query) {
        List<OrderRestModel> orderRestModels = new ArrayList<>();

        List<OrderEntity> storeOrder = orderRepository.findAll();
        for(OrderEntity entity : storeOrder) {
            OrderRestModel orderRestModel = new OrderRestModel();
            BeanUtils.copyProperties(entity, orderRestModel);
            orderRestModels.add(orderRestModel);
        }

        return orderRestModels;
    }
}
