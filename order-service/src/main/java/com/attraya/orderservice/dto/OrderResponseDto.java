package com.attraya.orderservice.dto;

import com.attraya.orderservice.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private Order order;
    private PaymentDto paymentDto;
    private UserDto userDto;
}
