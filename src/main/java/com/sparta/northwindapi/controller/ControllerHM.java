package com.sparta.northwindapi.controller;

import com.sparta.northwindapi.entities.Product;
import com.sparta.northwindapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ControllerHM {

    private ProductRepository productRepository;

    @Autowired
    public ControllerHM(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products/all")
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @PatchMapping("/products/update/{id}")
    public Product updateUnitPrice(@PathVariable int id, @RequestParam BigDecimal unitPrice) {
        Product product = null;
        if(productRepository.findById(id).isPresent()) {
            product = productRepository.findById(id).get();
            product.setUnitPrice(unitPrice);
            productRepository.save(product);
        }
        return product;
    }

}
