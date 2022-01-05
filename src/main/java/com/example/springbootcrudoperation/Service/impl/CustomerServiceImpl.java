package com.example.springbootcrudoperation.Service.impl;

import com.example.springbootcrudoperation.Service.CustomerService;
import com.example.springbootcrudoperation.Service.impl.entity.CustomerEntity;
import com.example.springbootcrudoperation.repository.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

private  final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public List<CustomerEntity> findAllCustomer() {
        return customerRepo.findAll();
    }

    @Override
    public Optional<CustomerEntity> findById(int id) {
        return customerRepo.findById(id);
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
        return customerRepo.save(customerEntity);
    }

    @Override
    public CustomerEntity updateCustomer(CustomerEntity customerEntity) {
        return customerRepo.save(customerEntity);
    }


    @Override
    public void deleteById(int id) {
        customerRepo.deleteById(id);
    }

}
