package com.example.productorder.domain;

import com.example.productorder.dto.ProductRequestDto;
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

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void update(ProductRequestDto request) {
        this.name = request.getName();
        this.price = request.getPrice();
    }
}