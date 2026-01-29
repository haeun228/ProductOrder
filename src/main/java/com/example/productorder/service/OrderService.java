package com.example.productorder.service;

import com.example.productorder.domain.Order;
import com.example.productorder.domain.Product;
import com.example.productorder.dto.OrderRequestDto;
import com.example.productorder.dto.OrderResponseDto;
import com.example.productorder.repository.OrderRepository;
import com.example.productorder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderResponseDto createOrder(OrderRequestDto request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        Order order = new Order(product);
        orderRepository.save(order);

        return OrderResponseDto.from(order);
    }

    public OrderResponseDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));

        return OrderResponseDto.from(order);
    }
}
