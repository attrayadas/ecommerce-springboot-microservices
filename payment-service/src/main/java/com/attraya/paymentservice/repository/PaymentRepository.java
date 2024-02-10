package com.attraya.paymentservice.repository;

import com.attraya.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByOrderId(String orderId);
}
