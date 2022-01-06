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
    private CustomerController itemController;

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    CustomerService customerService;
    //Mocking the service layer



    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(itemController)
                .build();
    }

    @Test
    public void findAllCustomer() throws Exception {
        List<CustomerEntity> items = Arrays.asList(new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true));
        when(customerService.findAllCustomer()).thenReturn(items);
        mockMvc.perform(MockMvcRequestBuilders.get("/customer"))
                .andExpect(status().isOk());

    }

    @Test
    public void findCustomerByID() throws Exception{
       CustomerEntity item = new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true);
        when(customerService.findById(item.getId())).thenReturn(Optional.of(item));
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fname").value("priya"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lname").value("sk"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("female"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(9999))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("psk@gmail"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.is_Active").value(true));
        Mockito.verify(customerService).findById(item.getId());
    }

    @Test
    public void saveCustomer() throws JsonProcessingException, Exception{

        CustomerEntity item = new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true);

        when(customerService.saveCustomer(any(CustomerEntity.class))).thenReturn(item);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .content(new ObjectMapper().writeValueAsString(item))
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
        CustomerEntity item = new CustomerEntity(5,"priya","sk","female",9999,"psk@gmail",true);

        when(customerService.saveCustomer(any(CustomerEntity.class))).thenReturn(item);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .content(new ObjectMapper().writeValueAsString(item))
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
