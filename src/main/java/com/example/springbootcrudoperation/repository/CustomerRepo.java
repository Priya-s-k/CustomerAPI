package com.example.springbootcrudoperation.repository;

import com.example.springbootcrudoperation.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {

}
