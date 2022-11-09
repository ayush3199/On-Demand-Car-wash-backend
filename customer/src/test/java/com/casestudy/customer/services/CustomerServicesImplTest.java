package com.casestudy.customer.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.casestudy.customer.models.Customer;
import com.casestudy.customer.repository.CustomerRepo;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { CustomerServicesImplTest.class })
public class CustomerServicesImplTest {
    @Mock
    private CustomerRepo customerRepo;

    @InjectMocks
    private CustomerServicesImpl customerServicesImpl;

    public List<Customer> mycustomer;

    @Test
    @Order(1)
    void testAddCustomer() {
        mycustomer = new ArrayList<Customer>();
        mycustomer.add(new Customer(101, "ayush", "Singh", "asingh@gmail.com", "Bareilly", "8171979905", "Abc@321"));

        when(customerRepo.findAll()).thenReturn(mycustomer);
        assertEquals(1, customerServicesImpl.getAllCustomers().size());

    }

    @Test
    @Order(2)
    void testDeleteCustomer() {
        Customer customer = new Customer(101, "ayush", "Singh", "asingh@gmail.com", "Bareilly", "8171979905",
                "Abc@321");
        customerServicesImpl.deleteCustomer(customer);
        verify(customerRepo, times(1)).delete(customer);

    }

    @Test
    @Order(3)
    void testGetAllCustomers() {
        mycustomer = new ArrayList<Customer>();
        mycustomer.add(new Customer(101, "ayush", "Singh", "asingh@gmail.com", "Bareilly", "8171979905", "Abc@321"));
        when(customerRepo.findAll()).thenReturn(mycustomer);
        assertEquals(1, customerServicesImpl.getAllCustomers().size());

    }

    @Test
    @Order(4)
    void testGetCustomerById() {
        Customer mycustomer = new Customer(101, "ayush", "Singh", "asingh@gmail.com", "Bareilly", "8171979905",
                "Abc@321");
        int customerId = 101;
        when(customerRepo.findCustomerBycustomerId(customerId)).thenReturn(mycustomer);
        assertEquals(customerId, customerServicesImpl.getCustomerById(customerId).getCustomerId());

    }
    @Test
    @Order(5)
    void testGetCustomerByEmail() {
        Customer mycustomer = new Customer(101, "ayush", "Singh", "asingh@gmail.com", "Bareilly", "8171979905",
                "Abc@321");
        String email = "asingh@gmail.com";
        when(customerRepo.findCustomerByEmail(email)).thenReturn(mycustomer);
        assertEquals(email, customerServicesImpl.getCustomerByEmail(email).getemail());

    }

    @Test
    @Order(6)
    void testUpdateCustomer() {
        Customer mycustomer = new Customer(101, "ayush", "Singh", "asingh@gmail.com", "Bareilly", "8171979905",
                "Abc@321");
        int cusId=101;
        when(customerServicesImpl.getCustomerById(cusId)).thenReturn(mycustomer);
        when(customerServicesImpl.updateCustomer(mycustomer)).thenReturn(mycustomer);
        assertEquals(mycustomer, customerServicesImpl.addCustomer(mycustomer));

    }
}
