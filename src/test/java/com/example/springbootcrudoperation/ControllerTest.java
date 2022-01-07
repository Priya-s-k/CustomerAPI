package com.example.springbootcrudoperation;

import com.example.springbootcrudoperation.Service.CustomerService;
import com.example.springbootcrudoperation.entity.CustomerEntity;
import com.example.springbootcrudoperation.controller.CustomerController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CustomerController.class)
public class ControllerTest {


    @Autowired
    private CustomerController customerController;

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    CustomerService customerService;
    //Mocking the service layer



    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .build();
    }

    @Test
    public void findAllCustomer() throws Exception {
        List<CustomerEntity> customerEntityList = Arrays.asList(new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true));
        when(customerService.findAllCustomer()).thenReturn(customerEntityList);
        mockMvc.perform(MockMvcRequestBuilders.get("/customer"))
                .andExpect(status().isOk());

    }

    @Test
    public void findCustomerByID() throws Exception{
       CustomerEntity customerEntity = new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true);
        when(customerService.findById(customerEntity.getId())).thenReturn(Optional.of(customerEntity));
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fname").value("priya"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lname").value("sk"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("female"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(9999))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("psk@gmail"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.is_Active").value(true));
        Mockito.verify(customerService).findById(customerEntity.getId());
    }

    @Test
    public void saveCustomer() throws JsonProcessingException, Exception{

        CustomerEntity customerEntity = new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true);

        when(customerService.saveCustomer(any(CustomerEntity.class))).thenReturn(customerEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .content(new ObjectMapper().writeValueAsString(customerEntity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fname").value("priya"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lname").value("sk"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("female"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(9999))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("psk@gmail"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.is_Active").value(true));
              }


    @Test
    public void updateCustomer() throws Exception {
        CustomerEntity customerEntity = new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true);

        when(customerService.saveCustomer(any(CustomerEntity.class))).thenReturn(customerEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .content(new ObjectMapper().writeValueAsString(customerEntity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fname").value("priya"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lname").value("sk"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("female"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(9999))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("psk@gmail"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.is_Active").value(true));
    }

    @Test
    public void deleteCustomer() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
