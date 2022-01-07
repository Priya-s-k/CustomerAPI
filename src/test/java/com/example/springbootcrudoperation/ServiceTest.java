package com.example.springbootcrudoperation;

import com.example.springbootcrudoperation.Service.CustomerServiceImpl;
import com.example.springbootcrudoperation.entity.CustomerEntity;
import com.example.springbootcrudoperation.repository.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ServiceTest
{
    @Mock
    private CustomerRepo customerRepo;

    @Autowired
    @InjectMocks
    private CustomerServiceImpl customerService;
    private CustomerEntity customerEntity;
    @BeforeEach
    public void setUp() {

      customerEntity = new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true);

    }
    @Test
    void SaveCustomerTest() throws Exception {

        //stubbing
        when(customerRepo.save(any())).thenReturn(customerEntity);
       customerService.saveCustomer(customerEntity);
        verify(customerRepo,times(1)).save(any());

    }
    @Test
    public void FindCustomerById() {

        Mockito.when(customerRepo.findById(5)).thenReturn(Optional.ofNullable((customerEntity)));
        assertThat(customerService.findById(customerEntity.getId())).isEqualTo(Optional.ofNullable(customerEntity));
    }
    @Test
    public void FindAllCustomer(){
        List<CustomerEntity> customerEntityList = Arrays.asList(new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true));
        customerRepo.save(customerEntity);
        when(customerRepo.findAll()).thenReturn(customerEntityList);
        List<CustomerEntity> productList1 =customerService.findAllCustomer();
        assertEquals(productList1,customerEntityList);
        verify(customerRepo,times(1)).save(customerEntity);
        verify(customerRepo,times(1)).findAll();
    }
    @Test
    public  void DeleteCustomer()
    {
      final int id=5;
      customerService.deleteById(id);
      verify(customerRepo,times(1)).deleteById(id);
    }





}
