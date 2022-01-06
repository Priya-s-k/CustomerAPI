package com.example.springbootcrudoperation.controller;

import com.example.springbootcrudoperation.Service.CustomerService;
import com.example.springbootcrudoperation.entity.CustomerEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping
    public List<CustomerEntity> findAllCustomer() {
        return customerService.findAllCustomer();
    }

    @GetMapping("/{id}")
    public Optional<CustomerEntity> findCustomerById(@PathVariable("id") int id) {
        return customerService.findById(id);
    }

    @PostMapping
    public CustomerEntity saveCustomer(@RequestBody CustomerEntity customerEntity) {
        return customerService.saveCustomer(customerEntity);
    }

    @PutMapping
    public CustomerEntity updateCustomer(@RequestBody CustomerEntity customerEntity) {
        return customerService.updateCustomer(customerEntity);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        customerService.deleteById(id);
    }


}
