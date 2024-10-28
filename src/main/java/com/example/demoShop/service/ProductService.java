package com.example.demoShop.service;

import com.example.demoShop.model.Product;
import com.example.demoShop.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceRange(minPrice, maxPrice);
    }

    public Double getMinPrice() {
        return productRepository.getMinPrice();
    }

    public Double getMaxPrice() {
        return productRepository.getMaxPrice();
    }
}
