package com.example.springbootcrudoperation.IntegrationTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.springbootcrudoperation.Application;
import com.example.springbootcrudoperation.Service.CustomerServiceImpl;
import com.example.springbootcrudoperation.entity.CustomerEntity;
import java.util.List;

@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ServiceIntegrationTesting {

    @Autowired
    CustomerServiceImpl customerService;
    CustomerEntity customerEntity;
    CustomerEntity persistedCustomer;

    @BeforeEach
    public void setup()
    {

        customerEntity = new CustomerEntity(50,"priya","sk","female",9999,"psk@gmail",true);
        persistedCustomer = customerService.saveCustomer(customerEntity);

  }
    @Order(1)
    @Test
    public void saveCustomerTest() {

        assertNotNull(persistedCustomer);
        assertEquals(customerEntity.getFname(), persistedCustomer.getFname());
        assertEquals(customerEntity.getLname(), persistedCustomer.getLname());
    }
    @Order(2)
    @Test
    public void GetCustomerByIDTest() {


        assertNotNull(customerService.findById(persistedCustomer.getId()));
        assertEquals(customerEntity.getId(),persistedCustomer.getId());
        assertEquals(customerEntity.getFname(), persistedCustomer.getFname());

    }
    @Order(3)
    @Test
    public void GetAllCustomerListExistsTest() {
        List<CustomerEntity> customerEntityList =customerService.findAllCustomer();
        assertNotNull(customerEntityList);
        assertThat(customerEntityList.size()).isGreaterThan(0);
    }
    @Order(4)
    @Test
    public void updateCustomerTest() {
        customerEntity.setFname("Priyanka");
        persistedCustomer = customerService.saveCustomer(customerEntity);

        assertNotNull(persistedCustomer);
        assertEquals(customerEntity.getFname(), persistedCustomer.getFname());

    }
    @Order(5)
    @Test
    public void deleteCustomerTest() {

        assertNull(customerService.deleteById(persistedCustomer.getId()));
    }



}