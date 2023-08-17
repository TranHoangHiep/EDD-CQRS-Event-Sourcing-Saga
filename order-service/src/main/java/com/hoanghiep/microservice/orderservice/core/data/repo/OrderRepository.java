package com.hoanghiep.microservice.orderservice.core.data.repo;

import com.hoanghiep.microservice.orderservice.core.data.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
