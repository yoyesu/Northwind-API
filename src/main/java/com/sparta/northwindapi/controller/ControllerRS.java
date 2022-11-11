package com.sparta.northwindapi.controller;
import com.sparta.northwindapi.entities.Customer;
import com.sparta.northwindapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerRS {

    private CustomerRepository customerRepo;
    @Autowired
    public ControllerRS(CustomerRepository customerRepo){
        this.customerRepo = customerRepo;
    }

    @GetMapping("/customer/all")
    public List<Customer> getAllCustomers(){
        List<Customer> customerList = customerRepo.findAll();
        return customerList;
    }

    @GetMapping("/customer/{contactName}")
    public Customer getCustomerByName(@PathVariable String contactName){
        List<Customer> customerList = customerRepo.findAll();
        for(Customer customer : customerList){
            if((customer.getContactName()).replace(" ","").equals(contactName)){
                return customer;
            }
        }
        return null;
    }




}
