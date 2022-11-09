package com.casestudy.customer.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.casestudy.customer.models.Customer;
import com.casestudy.customer.services.CustomerServicesImpl;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = { CustomerControllerTest.class })
public class CustomerControllerTest {
    @Mock
    private CustomerServicesImpl customerServicesImpl;
    @InjectMocks
    private CustomerController customerController;

    List<Customer> mycustomer;
    Customer customer;

    @Test
    @Order(1)
    void testAddCustomer() {
        customer = new Customer(101, "ayush", "Singh", "asingh@gmail.com", "Bareilly", "8171979905", "Abc@321");

        when(customerServicesImpl.addCustomer(customer)).thenReturn(customer);
        ResponseEntity<Customer> res = customerController.addCustomer(customer);
        assertEquals(HttpStatus.CREATED, res.getStatusCode());
        assertEquals(customer, res.getBody());

    }

     @Test
     @Order(2)
     void testDeleteCustomer() {
     customer = new Customer(101, "ayush", "Singh", "asingh@gmail.com",
     "Bareilly", "8171979905", "Abc@321");
     int cusId = 101;
     when(customerServicesImpl.getCustomerById(cusId)).thenReturn(customer);
     assertEquals(customer.getCustomerId(),cusId);
     ResponseEntity<Customer> res = customerController.deleteCustomer(cusId);
     assertEquals(HttpStatus.OK, res.getStatusCode());
     assertEquals(cusId, res.getBody().getCustomerId());

     }

    @Test
    @Order(3)
    void testGetAllCustomers() {
        mycustomer = new ArrayList<Customer>();
        mycustomer.add(new Customer(101, "ayush", "Singh", "asingh@gmail.com", "Bareilly", "8171979905", "Abc@321"));
        mycustomer.add(new Customer(102, "nikil", "Srivastava", "nsrih@gmail.com", "Bareilly", "985746123", "Abc@321"));

        when(customerServicesImpl.getAllCustomers()).thenReturn(mycustomer);
        ResponseEntity<List<Customer>> res = customerController.getAllCustomers();
        assertEquals(HttpStatus.FOUND, res.getStatusCode());
        assertEquals(2, res.getBody().size());

    }

     @Test
     @Order(4)
     void testGetCustomerById() {
     customer = new Customer(101, "ayush", "Singh", "asingh@gmail.com",
     "Bareilly", "8171979905", "Abc@321");
     int cusId = 101;
     when(customerServicesImpl.getCustomerById(cusId)).thenReturn(customer);
     ResponseEntity<Customer> res = customerController.getCustomerById(cusId);

     assertEquals(HttpStatus.OK, res.getStatusCode());
     assertEquals(cusId, res.getBody().getCustomerId());

     }

    @Test
    @Order(5)
    void testUpdateCustomer() {
        customer = new Customer(101, "ayush", "Singh", "asingh@gmail.com", "Bareilly", "8171979905", "Abc@321");
        int cusId = 101;
        when(customerServicesImpl.getCustomerById(cusId)).thenReturn(customer);
        when(customerServicesImpl.updateCustomer(customer)).thenReturn(customer);
        assertEquals(customer.getCustomerId(), cusId);

        ResponseEntity<Customer> res = customerController.updateCustomer(cusId, customer);
        assertEquals(HttpStatus.OK, res.getStatusCode());

    }
}
