package com.example.productorder.controller;

import com.example.productorder.dto.ProductRequestDto;
import com.example.productorder.dto.ProductResponseDto;
import com.example.productorder.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto request) {
        return productService.createProduct(request);
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @GetMapping
    public List<ProductResponseDto> getProductList() {
        return productService.getProductList();
    }

    @PatchMapping("/{productId}")
    public ProductResponseDto updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDto request) {
        return productService.updateProduct(productId, request);
    }

    @DeleteMapping("/{productId}")
    public void deletePRoduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

}
