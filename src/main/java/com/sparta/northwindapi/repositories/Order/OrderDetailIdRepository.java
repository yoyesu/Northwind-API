package com.sparta.northwindapi.repositories.Order;

import com.sparta.northwindapi.entities.Order.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailIdRepository extends JpaRepository<OrderDetailId, Integer> {
}
