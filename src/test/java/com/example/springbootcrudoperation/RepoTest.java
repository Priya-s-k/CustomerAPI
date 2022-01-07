package com.example.springbootcrudoperation;
import com.example.springbootcrudoperation.Service.CustomerServiceImpl;
import com.example.springbootcrudoperation.entity.CustomerEntity;
import com.example.springbootcrudoperation.repository.CustomerRepo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Configuration
@EnableJpaRepositories(basePackages = "com.developer.customerapi.Repository")
@PropertySource("persistence-generic-entity.properties")
@EnableTransactionManagement
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepoTest {
    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    private CustomerEntity customerEntity;

    @Mock
    private CustomerRepo customerRepository;
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
	public void testGetCustomerById() {
	
		 CustomerEntity customerEntity = new CustomerEntity(1,"priya","sk","female",9999,"psk@gmail",true);
		
		 customerRepository.save(customerEntity);
		
	
		when(customerRepository.findById(customerEntity.getId())).thenReturn(Optional.of(customerEntity));
		
		Optional<CustomerEntity> customerEnt=customerRepository.findById(customerEntity.getId());
		
		assertNotNull(customerEnt);
		
		assertEquals("priya",customerEnt.get().getFname());
	}
	
    
    

	@Test
	public void testGetAllCustomers() {
		
		 CustomerEntity customerEntity1 = new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true);
		 CustomerEntity customerEntity2 = new CustomerEntity(6,"ram","mohan","male",99909,"ram@gmail",true);
		 customerRepository.save(customerEntity1);
		 customerRepository.save(customerEntity2);
	
		
		 List<CustomerEntity> customers=(List<CustomerEntity>)customerRepository.findAll();
		 for(CustomerEntity cust:customers)
			 System.out.println(cust);
	     Assertions.assertThat(customers).size().isGreaterThan(0);
	
	}
	
    @Test
	@Order(1)
	public void testSaveCustomer(){
		CustomerEntity customerEntity= new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true);
		customerRepository.save(customerEntity);
		assertNotNull(customerEntity.getId());
	}
	

    @Test
	public void TestDeleteCustomer(){
	    CustomerEntity customerEntity = new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true);
	    customerRepository.save(customerEntity);
	 
	    customerRepository.deleteById(customerEntity.getId());
	    Optional<CustomerEntity> optional = customerRepository.findById(customerEntity.getId());
	    assertEquals(Optional.empty(), optional);
	}
	




}
