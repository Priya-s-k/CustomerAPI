package com.example.springbootcrudoperation.Service;

import com.example.springbootcrudoperation.Service.impl.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
   List<CustomerEntity> findAllCustomer();
    Optional<CustomerEntity> findById(int id);
   CustomerEntity saveCustomer(CustomerEntity customerEntity);
   CustomerEntity updateCustomer(CustomerEntity customerEntity);
    void deleteById(int id);
}
