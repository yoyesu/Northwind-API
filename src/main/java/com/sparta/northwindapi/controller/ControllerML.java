package com.sparta.northwindapi.controller;

import com.sparta.northwindapi.entities.Order.Order;
import com.sparta.northwindapi.repositories.Order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerML {

    private OrderRepository orderRepository;

    @Autowired
    public ControllerML(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders/{id}")
    public Order getOrdersById(@PathVariable int id){
        Order listOfOrders = orderRepository.findById(id).get();
        return listOfOrders;
    }

    @GetMapping("/orders/range")
    public List<Order> getOrdersByOrderDateRange(
                         @RequestParam("from")
                        String startDate,
                       @RequestParam("to")
                       String endDate){


//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startTimeDate = LocalDate.parse(startDate);

        LocalDate endTimeDate = LocalDate.parse(endDate);

        List<Order> listOfOrders = orderRepository.findAll();
        List<Order> ordersInRange = new ArrayList<>();

        for(Order order : listOfOrders){

            LocalDate orderDate = LocalDate.ofInstant(order.getOrderDate(), ZoneOffset.UTC);

            if(orderDate.isAfter(startTimeDate) && orderDate.isBefore(endTimeDate) ){
                ordersInRange.add(order);
            }
        }
        return ordersInRange;
    }
}
