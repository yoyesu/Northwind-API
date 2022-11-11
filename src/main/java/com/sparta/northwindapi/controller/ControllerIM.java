package com.sparta.northwindapi.controller;

import com.sparta.northwindapi.entities.Customer;
import com.sparta.northwindapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ControllerIM {

    private CustomerRepository customerRepo;


    @Autowired
    public ControllerIM(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping("/customer/all")
    public List<Customer> getAllCustomers(){
        List<Customer> customerList = customerRepo.findAll();
        return customerList;
    }

    @GetMapping("/customer/{id}")
    public EntityModel<Customer> getCustomerById (@PathVariable String id){
        List<Customer> customerList = customerRepo.findAll();
        for (Customer customer: customerList){
            if (customer.getId().equals(id)){
                EntityModel<Customer> entityModel=EntityModel.of(customer);
                WebMvcLinkBuilder webMvcLinkBuilder= WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllCustomers());
                entityModel.add(webMvcLinkBuilder.withRel("all-customers"));
                WebMvcLinkBuilder webMvcLinkBuilder1= WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getCustomerById(id));
                entityModel.add(webMvcLinkBuilder1.withRel("this-customer"));

                return entityModel;
            }
        }
        return null;
    }


}
