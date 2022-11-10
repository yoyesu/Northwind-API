package com.sparta.northwindapi.repositories;

import com.sparta.northwindapi.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
