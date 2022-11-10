package com.sparta.northwindapi.repositories;

import com.sparta.northwindapi.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
