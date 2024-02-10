package com.attraya.orderservice.service;

import com.attraya.orderservice.controller.OrderController;
import com.attraya.orderservice.dto.OrderResponseDto;
import com.attraya.orderservice.dto.PaymentDto;
import com.attraya.orderservice.dto.UserDto;
import com.attraya.orderservice.entity.Order;
import com.attraya.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository repository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${order.producer.topic.name}")
    private String topicName;

    @Autowired
    private RestTemplate restTemplate;

    public String placeOrder(Order order){
        logger.info("OrderService.placeOrder Order : {}", order);
        // save a copy in order-service DB
        order.setPurchaseDate(new Date());
        order.setOrderId(UUID.randomUUID().toString().split("-")[0]);
        repository.save(order);

        // send it to payment service using Kafka
        kafkaTemplate.send(topicName, order);
        return "Your order with ("+order.getOrderId()+") has been placed! Will notify once it will confirm :)";
    }

    // get order
    public OrderResponseDto getOrder(String orderId){
        logger.info("OrderService.getOrder Order ID : {}", orderId);
        // own DB -> ORDER
        Order order = repository.findByOrderId(orderId);

        // PAYMENT -> REST call payment-service
        PaymentDto paymentDto = restTemplate.getForObject("http://localhost:9292/payments/" + orderId, PaymentDto.class);

        // user-info -> rest call user-service
        UserDto userDto = restTemplate.getForObject("http://localhost:9393/users/" + order.getUserId(), UserDto.class);

        return OrderResponseDto.builder()
                .order(order)
                .userDto(userDto)
                .paymentDto(paymentDto)
                .build();
    }
}
