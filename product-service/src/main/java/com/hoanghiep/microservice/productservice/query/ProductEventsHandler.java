package com.hoanghiep.microservice.productservice.query;

import com.hoanghiep.microservice.productservice.core.data.ProductEntity;
import com.hoanghiep.microservice.productservice.core.data.ProductRepository;
import com.hoanghiep.microservice.productservice.core.events.ProductCreatedEvent;
import com.hoanghiep.mucroservice.core.event.ProductReservedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@RequiredArgsConstructor
public class ProductEventsHandler {

    private final ProductRepository productRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) {

        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(event, entity);
        productRepository.save(entity);

    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent) {
        ProductEntity productEntity = productRepository.findByProductId(productReservedEvent.getProductId());
        productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());
        productRepository.save(productEntity);
    }
}
