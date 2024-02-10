package com.attraya.paymentservice.service;

import com.attraya.paymentservice.entity.Payment;
import com.attraya.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment getOrderById(String orderId){
        return paymentRepository.findByOrderId(orderId);
    }
}
