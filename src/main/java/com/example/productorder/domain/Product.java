package com.example.productorder.domain;

import com.example.productorder.dto.ProductRequestDto;
import com.example.productorder.exception.CustomException;
import com.example.productorder.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void update(ProductRequestDto request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.stock = request.getStock();
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new CustomException(ErrorCode.INVALID_REQUEST);
        }
        if (stock < quantity) {
            throw new CustomException(ErrorCode.OUT_OF_STOCK);
        }
        stock -= quantity;
    }

}