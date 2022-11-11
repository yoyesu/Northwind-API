package com.sparta.northwindapi.controller;

import com.sparta.northwindapi.entities.Customer;
import com.sparta.northwindapi.entities.Order.Order;
import com.sparta.northwindapi.repositories.CustomerRepository;
import com.sparta.northwindapi.repositories.Order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public CollectionModel<Order> getOrdersByOrderDateRange(
            @RequestParam("from")
            String startDate,
            @RequestParam("to")
            String endDate){

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
//        for (Order order : ordersInRange){
//            Link linkBuilder = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getCustomerById(order.getCustomerID())).withSelfRel();
//            order.add(linkBuilder);
//        }
//        Link link = linkTo(methodOn(ControllerML.class).getOrdersByOrderDateRange(startDate, endDate)).withSelfRel();
        CollectionModel<Order> entityOrderList = CollectionModel.of(ordersInRange);

        return entityOrderList;
    }


    private CustomerRepository customerRepo;

    @GetMapping("/customer/{id}")
    public Customer getCustomerById (@PathVariable String id){
        List<Customer> customerList = customerRepo.findAll();
        for (Customer customer: customerList){
            if (customer.getId().equals(id)){
                return customer;
            }
            break;
        }
        return null;
    }
}
