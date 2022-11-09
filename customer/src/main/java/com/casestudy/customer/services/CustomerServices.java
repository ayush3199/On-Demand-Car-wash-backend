package com.casestudy.customer.services;

import java.util.List;

import com.casestudy.customer.models.Customer;

public interface CustomerServices {

    public Customer addCustomer(Customer customer);

    public List<Customer> getAllCustomers();

    public Customer getCustomerById(int customerId);

    public Customer updateCustomer(Customer customer);

    public void deleteCustomer(Customer customer);

    public Customer getCustomerByEmail(String email);

}
