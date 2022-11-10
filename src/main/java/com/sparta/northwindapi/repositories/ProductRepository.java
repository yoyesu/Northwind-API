package com.sparta.northwindapi.repositories;

import com.sparta.northwindapi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
