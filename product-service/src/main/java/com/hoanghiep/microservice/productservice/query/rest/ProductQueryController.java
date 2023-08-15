package com.hoanghiep.microservice.productservice.query.rest;

import com.hoanghiep.microservice.productservice.query.FindProductQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductQueryController {

    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<ProductRestModel> getProducts() {
        log.info("===== Start get products =====");
        FindProductQuery findProductQuery = new FindProductQuery();
        List<ProductRestModel> products = queryGateway.query(findProductQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
        log.info(products.toString());
        log.info("===== End get products =====");
        return products;
    }
}
