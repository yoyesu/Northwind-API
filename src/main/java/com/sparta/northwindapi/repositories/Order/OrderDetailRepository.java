package com.sparta.northwindapi.repositories.Order;

import com.sparta.northwindapi.entities.Order.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
