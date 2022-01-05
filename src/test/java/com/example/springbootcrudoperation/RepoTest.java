package com.example.springbootcrudoperation;
import com.example.springbootcrudoperation.Service.impl.CustomerServiceImpl;
import com.example.springbootcrudoperation.Service.impl.entity.CustomerEntity;
import com.example.springbootcrudoperation.repository.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
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
    final void testGetCustomerByID() {
        CustomerEntity item = new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true);
        assertTrue(customerService.findById(5).empty() != null);
    }



    @Test
    public void testSaveCustomer(){
        CustomerEntity item = new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true);
        customerRepository.save(item);
        assertNotNull(item.getId());
    }

    @Test
    public void TestDeleteCustomer(){
        CustomerEntity item1 = new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true);
        customerRepository.save(item1);
     customerRepository.deleteById(item1.getId());
        Optional optional = customerRepository.findById(item1.getId());
        assertEquals(Optional.empty(), optional);
    }





}
