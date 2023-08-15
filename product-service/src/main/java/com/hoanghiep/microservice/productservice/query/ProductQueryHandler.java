package com.hoanghiep.microservice.productservice.query;

import com.hoanghiep.microservice.productservice.core.data.ProductEntity;
import com.hoanghiep.microservice.productservice.core.data.ProductRepository;
import com.hoanghiep.microservice.productservice.query.rest.ProductRestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductQuery query) {
        List<ProductRestModel> productRestModels = new ArrayList<>();

        List<ProductEntity> storedProduct = productRepository.findAll();
        for(ProductEntity productEntity : storedProduct) {
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            productRestModels.add(productRestModel);
        }

        return productRestModels;
    }

}
