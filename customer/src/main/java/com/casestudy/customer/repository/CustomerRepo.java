package com.casestudy.customer.repository;

import com.casestudy.customer.models.Customer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends MongoRepository<Customer, Integer> {

    public Customer findCustomerBycustomerId(int customerId);

    public Customer findByEmailAndPassword(String email, String password);

    public Customer findCustomerByEmail(String email);
}
