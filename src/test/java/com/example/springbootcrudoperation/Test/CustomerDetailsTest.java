package com.example.springbootcrudoperation.Test;

import com.example.springbootcrudoperation.Application;
import com.example.springbootcrudoperation.Service.CustomerServiceImpl;
import com.example.springbootcrudoperation.entity.CustomerEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerDetailsTest {
    @Autowired
    CustomerServiceImpl customerService;
    CustomerEntity customerEntity;
    CustomerEntity persistedCustomer;

    @BeforeEach
    public void setup()
    {
        customerEntity = new CustomerEntity(55,"priya","sk","female",9999,"psk@gmail.com",true);

    }
    @Order(6)
    @Test
    public void CheckCustomerExistById() {
        persistedCustomer = customerService.saveCustomer(customerEntity);
        assertNotNull(customerService.findById(persistedCustomer.getId()));
        assertEquals(customerEntity.getId(),persistedCustomer.getId());
    }
    @Order(5)
    @Test
    public void  CheckDuplicateIdExist()
    {
        persistedCustomer = customerService.saveCustomer(customerEntity);
        CustomerEntity customerEntity1= new CustomerEntity(55,"priya","sk","female",9999,"psk@gmail.com",true);

        int f=0;
        List<CustomerEntity> customerEntityList =customerService.findAllCustomer();
        for (CustomerEntity customerEntityList1:customerEntityList
        ) {
            if(customerEntityList1.getId()==customerEntity1.getId())
                f=1;
        }
        assertEquals(f,1);
        System.out.println("duplicate id entered");
    }

    @Order(1)
    @Test
    public void CheckingIdisNull()
    {
        assertNotNull(customerEntity.getId());
    }
    @Order(2)
    @Test
    public void CheckingEmailisNull()
    {
        assertNotNull(customerEntity.getEmail());
    }

    @Order(3)
    @Test
    public void EmailFormatValidation()
    {
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        assertTrue(customerEntity.getEmail().matches(regex));
    }
    @Order(4)
   @Test
    public void ActiveUser()
   {
       assertNotNull(customerEntity.isIs_Active());
   }



}