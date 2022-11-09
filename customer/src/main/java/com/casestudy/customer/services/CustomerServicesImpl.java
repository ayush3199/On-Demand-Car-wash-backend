package com.casestudy.customer.services;

import java.util.List;
import com.casestudy.customer.models.Customer;
import com.casestudy.customer.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class CustomerServicesImpl implements CustomerServices {
    @Autowired
    private CustomerRepo customerRepo;

    // mocking
    public CustomerServicesImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    // add Customer
    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    // get All Customers
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = (List<Customer>) this.customerRepo.findAll();
        return customers;
    }

    // get Customer by id
    @Override
    public Customer getCustomerById(int customerId) {
        Customer customer = null;
        try {
            customer = this.customerRepo.findCustomerBycustomerId(customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    // get customer by email
    @Override
    public Customer getCustomerByEmail(String email) {
        Customer customer = null;
        try {
            customer = this.customerRepo.findCustomerByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    // Delete Customer by id
    @Override
    public void deleteCustomer(Customer customer) {
        customerRepo.delete(customer);
    }

    // update customer by id
    @Override
    public Customer updateCustomer(Customer customer) {
        customerRepo.save(customer);
        return customer;
    }

}
