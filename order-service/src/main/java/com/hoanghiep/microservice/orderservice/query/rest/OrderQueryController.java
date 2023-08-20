package com.hoanghiep.microservice.orderservice.query.rest;

import com.hoanghiep.microservice.orderservice.query.FindOrderQuery;
import com.hoanghiep.microservice.orderservice.query.rest.model.OrderRestModel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderQueryController {

    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<OrderRestModel> getOrders() {
        log.info("==========> Start get Order <==========");
        FindOrderQuery findOrderQuery = new FindOrderQuery();
        List<OrderRestModel> orders = queryGateway.query(findOrderQuery,
                ResponseTypes.multipleInstancesOf(OrderRestModel.class)).join();
        log.info(orders.toString());
        log.info("==========> End get Order <==========");
        return orders;
    }
}
