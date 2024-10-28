package com.example.demoShop.controller;

import com.example.demoShop.model.Product;
import com.example.demoShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
//    відобраденння всього товару

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("minPrice", productService.getMinPrice());
        model.addAttribute("maxPrice", productService.getMaxPrice());
        return "product_list";
    }
//фільтрування
    @GetMapping("/products/filter")
    public String filterProducts(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            Model model) {

        Double dbMinPrice = productService.getMinPrice();
        Double dbMaxPrice = productService.getMaxPrice();

        // Перевірка введеного значення для мінімальної ціни
        if (minPrice == null || minPrice < dbMinPrice) {
            minPrice = dbMinPrice;
        }

        // Перевірка введеного значення для максимальної ціни
        if (maxPrice == null || maxPrice > dbMaxPrice) {
            maxPrice = dbMaxPrice;
        }

        // Отримуємо відфільтровані продукти в межах скоригованих цін
        List<Product> filteredProducts = productService.getProductsByPriceRange(minPrice, maxPrice);

        // Додаємо змінені значення мінімальної та максимальної ціни в модель
        model.addAttribute("products", filteredProducts);
        model.addAttribute("minPrice", minPrice);  // збереження значення для відображення у формі
        model.addAttribute("maxPrice", maxPrice);  // збереження значення для відображення у формі
        model.addAttribute("dbMinPrice", dbMinPrice);
        model.addAttribute("dbMaxPrice", dbMaxPrice);

        return "product_list";
    }
}