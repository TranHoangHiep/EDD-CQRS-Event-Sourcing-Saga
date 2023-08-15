package com.hoanghiep.microservice.productservice.command.rest;

import com.hoanghiep.microservice.productservice.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final Environment env;
    private final CommandGateway commandGateway;

    @PostMapping
    public String createProduct(@RequestBody CreateProductModel createProductModel) {

        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductModel.getPrice())
                .quantity(createProductModel.getQuantity())
                .title(createProductModel.getTitle())
                .productId(UUID.randomUUID().toString())
                .build();

        // send to command gateway
        String returnValue;
        try {
            returnValue = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception exception) {
            returnValue = exception.getLocalizedMessage();
        }

        return returnValue;
    }

//    @GetMapping
//    public String getProduct() {
//        return "HTTP GET Handled " + env.getProperty("local.server.port");
//    }

    @PutMapping
    public String updateProduct() {
        return "HTTP PUT Handled";
    }

    @DeleteMapping
    public String deleteProduct() {
        return "HTTP DELETE Handled";
    }
}
