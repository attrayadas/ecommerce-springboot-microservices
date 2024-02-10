package com.attraya.orderservice.controller;

import com.attraya.orderservice.dto.OrderResponseDto;
import com.attraya.orderservice.entity.Order;
import com.attraya.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderService service;

    @PostMapping
    public String placeNewOrder(@RequestBody Order order){
        return service.placeOrder(order);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable String orderId){
        logger.info("OrderController.getOrder Order ID : {}",orderId);
        return service.getOrder(orderId);
    }
}
