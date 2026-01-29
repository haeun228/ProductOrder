package com.example.productorder.service;

import com.example.productorder.domain.Product;
import com.example.productorder.dto.ProductRequestDto;
import com.example.productorder.dto.ProductResponseDto;
import com.example.productorder.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto request) {
        Product product = new Product(request.getName(), request.getPrice());
        productRepository.save(product);

        return ProductResponseDto.from(product);
    }

    public ProductResponseDto getProduct(Long productId) {
        Product product = findProductById(productId);
        return ProductResponseDto.from(product);
    }

    public List<ProductResponseDto> getProductList() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(ProductResponseDto::from)
                .toList();
    }

    @Transactional
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto request) {
        Product product = findProductById(productId);
        product.update(request);

        return ProductResponseDto.from(product);
    }

    public void deleteProduct(Long productId) {
        Product product = findProductById(productId);
        productRepository.delete(product);
    }

    private Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
    }
}
