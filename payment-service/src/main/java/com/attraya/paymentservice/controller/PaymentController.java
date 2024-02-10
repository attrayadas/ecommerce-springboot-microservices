package com.attraya.paymentservice.controller;

import com.attraya.paymentservice.entity.Payment;
import com.attraya.paymentservice.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private static Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService service;

    @GetMapping("/{orderId}")
    public Payment getPayment(@PathVariable String orderId){
        logger.info("PaymentController.getPayment orderId : {}",orderId);
        return service.getOrderById(orderId);
    }
}
