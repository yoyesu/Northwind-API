package com.sparta.northwindapi.controller;
import com.sparta.northwindapi.entities.Customer;
import com.sparta.northwindapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerRS {

    @Autowired
    private CustomerRepository customerRepo;

    @GetMapping("/customer/all")
    public List<Customer> getAllCustomers(){
        List<Customer> customerList = customerRepo.findAll();
        return customerList;
    }

    @GetMapping("/customer/customerName")
    public Customer getCustomerByName(@RequestBody String companyName){
        List<Customer> customerList = customerRepo.findAll();
        for(Customer customer :customerList){
            if(customer.getCompanyName().equals(companyName)){
                return customer;
            }
            break;
        }
        return null;
    }


}
