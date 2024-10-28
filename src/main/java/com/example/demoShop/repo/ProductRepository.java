package com.example.demoShop.repo;

import com.example.demoShop.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        System.out.println("Total products found: " + jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class)).size());
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public List<Product> findByPriceRange(Double minPrice, Double maxPrice) {
        String sql = "SELECT * FROM products WHERE price BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[]{minPrice, maxPrice}, new BeanPropertyRowMapper<>(Product.class));
    }

    public Double getMinPrice() {
        String sql = "SELECT MIN(price) FROM products";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public Double getMaxPrice() {
        String sql = "SELECT MAX(price) FROM products";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }
}
