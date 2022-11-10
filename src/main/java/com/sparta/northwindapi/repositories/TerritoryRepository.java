package com.sparta.northwindapi.repositories;

import com.sparta.northwindapi.entities.Territory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerritoryRepository extends JpaRepository<Territory, Integer> {
}
