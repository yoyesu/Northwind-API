package com.sparta.northwindapi.repositories;

import com.sparta.northwindapi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}