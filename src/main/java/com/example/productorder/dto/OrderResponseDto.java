package com.example.productorder.dto;

import com.example.productorder.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponseDto {

    private Long orderId;
    private Long productId;
    private String productName;
    private LocalDateTime orderedAt;

    public static OrderResponseDto from(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getProduct().getId(),
                order.getProduct().getName(),
                order.getOrderedAt()
        );
    }

}
