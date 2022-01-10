package com.example.springbootcrudoperation.Service;

import com.example.springbootcrudoperation.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
   List<CustomerEntity> findAllCustomer();
    Optional<CustomerEntity> findById(int id);
   CustomerEntity saveCustomer(CustomerEntity customerEntity);
   CustomerEntity updateCustomer(CustomerEntity customerEntity);
    Object deleteById(int id);
}
