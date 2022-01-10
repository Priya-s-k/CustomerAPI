package com.example.springbootcrudoperation.IntegrationTest;
import com.example.springbootcrudoperation.Application;
import com.example.springbootcrudoperation.entity.CustomerEntity;
import com.example.springbootcrudoperation.repository.CustomerRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepoIntegrationTest
{

    @Autowired
    private CustomerRepo repository;
    CustomerEntity customerEntity;
    CustomerEntity customerEntity1;
    @BeforeEach
    public void setup()
    {
        customerEntity= new CustomerEntity(40,"priyanka","sk","female",9999,"psk@gmail",true);
       customerEntity1= repository.save(customerEntity);
    }


    @Order(1)
    @Test
    public void SaveCustomerTest() {

        assertNotNull(customerEntity1);
    }

    @Order(2)
    @Test
    public void FindCustomerByIdTest() {

        Optional<CustomerEntity> customerEnt=repository.findById(customerEntity1.getId());

        Assertions.assertNotNull(customerEnt);

        assertEquals("priyanka",customerEnt.get().getFname());

    }
    @Order(3)
    @Test
    public void FindAllCustomerTest() {

        List<CustomerEntity> customerEnt=repository.findAll();

        Assertions.assertNotNull(customerEnt);

           assertThat(customerEnt.size()).isGreaterThan(0);
    }


    @Order(4)
    @Test
    public void UpdateCustomerTest() {
        customerEntity.setFname("Ramya");
        customerEntity1= repository.save(customerEntity);
        assertEquals("Ramya",customerEntity1.getFname());

    }
    @Order(5)
    @Test
    public void DeleteCustomerTest() {
        repository.deleteById(customerEntity1.getId());
        Optional<CustomerEntity> optional =repository.findById(customerEntity1.getId());
        assertEquals(Optional.empty(), optional);

    }




}
