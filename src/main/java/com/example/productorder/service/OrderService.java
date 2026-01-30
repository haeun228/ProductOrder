package com.example.productorder.service;

import com.example.productorder.domain.Order;
import com.example.productorder.domain.Product;
import com.example.productorder.dto.OrderRequestDto;
import com.example.productorder.dto.OrderResponseDto;
import com.example.productorder.exception.CustomException;
import com.example.productorder.exception.ErrorCode;
import com.example.productorder.repository.OrderRepository;
import com.example.productorder.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        product.decreaseStock(request.getQuantity());

        Order order = new Order(product, request.getQuantity());
        orderRepository.save(order);

        return OrderResponseDto.from(order);
    }

    public OrderResponseDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException(ErrorCode.ORDER_NOT_FOUND));

        return OrderResponseDto.from(order);
    }

    public PagedModel<OrderResponseDto> getOrderList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderResponseDto> orders = orderRepository.findAll(pageable).map(OrderResponseDto::from);

        return new PagedModel<>(orders);
    }
}
