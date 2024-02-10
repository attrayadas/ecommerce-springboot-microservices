package com.attraya.orderservice.repository;

import com.attraya.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByOrderId(String orderId);
}
