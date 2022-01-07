package com.example.IntegrationTest;

import com.example.springbootcrudoperation.Application;
import com.example.springbootcrudoperation.entity.CustomerEntity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Order(3)
    @Test
    public void testGetAllCustomer() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/customer",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }
    @Order(2)
    @Test
    public void testGetCustomerById() {
        CustomerEntity customerEntity = restTemplate.getForObject(getRootUrl() + "/customer/6", CustomerEntity.class);
        System.out.println(customerEntity.getFname());
        assertNotNull(customerEntity);
    }

    @Order(1)
    @Test
    public void testSaveCustomer() {
        CustomerEntity customerEntity = new CustomerEntity(6,"Kumari","nk","female",9999,"rutu@gmail",true);

        ResponseEntity<CustomerEntity> postResponse = restTemplate.postForEntity(getRootUrl() + "/customer", customerEntity, CustomerEntity.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }
    @Order((4))
    @Test
    public void testUpdateEmployee() {
        int id = 6;
        CustomerEntity customerEntity = restTemplate.getForObject(getRootUrl() + "/customer/" + id, CustomerEntity.class);
        customerEntity.setFname("VeerKumari");
        customerEntity.setLname("NK");
        restTemplate.put(getRootUrl() + "/customer/" + id, customerEntity);
        CustomerEntity updatedCustomer = restTemplate.getForObject(getRootUrl() + "/employees/" + id, CustomerEntity.class);
        assertNotNull(updatedCustomer);
    }

    @Order(5)
    @Test
    public void testDeleteEmployee() {
        int id = 6;
        CustomerEntity customerEntity = restTemplate.getForObject(getRootUrl() + "/customer/" + id, CustomerEntity.class);
        assertNotNull(customerEntity);
        restTemplate.delete(getRootUrl() + "/customer/" + id);
        try {
            customerEntity = restTemplate.getForObject(getRootUrl() + "/customer/" + id, CustomerEntity.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
