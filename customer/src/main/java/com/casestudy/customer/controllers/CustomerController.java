package com.casestudy.customer.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
//import java.util.logging.Logger;

import com.casestudy.customer.models.Car;
import com.casestudy.customer.models.Customer;
import com.casestudy.customer.repository.CustomerRepo;
import com.casestudy.customer.services.CustomerServices;
import com.casestudy.customer.services.CustomerServicesImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin
@RequestMapping("/customer")

public class CustomerController {
	Logger logger= (Logger) LoggerFactory.getLogger(CustomerController.class);
	// creating object using autowired
	@Autowired
	private CustomerServicesImpl custServiceiImpl;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CustomerRepo customerRepo; // for checking

	@PostMapping("/customerlogin")

	public ResponseEntity<Customer> getLogin(@RequestBody Customer customer) {

		String email = customer.getemail();
		String password = customer.getPassword();
		logger.debug("Request {}",customer);
		try {
			Customer obj = null;
			if (email != null && password != null) {
				obj = customerRepo.findByEmailAndPassword(email, password);
			}
			logger.debug("Response {}",obj);
			return new ResponseEntity<Customer>(obj, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	// for Adding new Customer

	@PostMapping("/add")

	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Customer c = null;
		logger.debug("Request {}",customer);
		try {
			c = this.custServiceiImpl.addCustomer(customer);
			logger.debug("Response {}",HttpStatus.CREATED);
			return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Response {}",HttpStatus.INTERNAL_SERVER_ERROR);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// Get All Customer
	@GetMapping("/getAll")
	public ResponseEntity<List<Customer>> getAllCustomers() {
//		logger.debug("Request {}");
		try {
			List<Customer> customers = custServiceiImpl.getAllCustomers();
			logger.debug("Response {}",HttpStatus.FOUND);
			return new ResponseEntity<List<Customer>>(customers, HttpStatus.FOUND);
		} catch (Exception e) {
			logger.debug("Response {}",HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Get Customer By Id
	@GetMapping("/get/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") int customerId) {
		Customer customer = custServiceiImpl.getCustomerById(customerId);
		if (customer == null) {
			logger.debug("Response {}",HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		logger.debug("Response {}",customer);
		return ResponseEntity.of(Optional.of(customer));

	}

	// Get Customer By emailId
	@GetMapping("/getemail/{email}")
	public ResponseEntity<Customer> getCustomerByEmailId(@PathVariable("email") String email) {
		Customer customer = custServiceiImpl.getCustomerByEmail(email);
		if (customer == null) {
			logger.debug("Response {}",HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		logger.debug("Response {}",customer);
		return ResponseEntity.of(Optional.of(customer));

	}

////	 Get Customer by customerId
//	 @GetMapping("/gets/{customerId}")
//	 public ResponseEntity<Customer> getCustomerByCId(@PathVariable("customerId")
//	 int customerId) {
//	 Customer customer = custServiceiImpl.getCustomerById(customerId);
////	  if (washer == null) {
////	  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
////	  }
////	  return ResponseEntity.of(Optional.of(washer));
//	 Car cars = this.restTemplate.getForObject("http://localhost:5002/car/get/" +
//	 customer.getCustomerId(), Car.class);
//	 customer.setCar(cars);
//	 return ResponseEntity.of(Optional.of(customer));
//
//	 }

	// Delete Customer by id
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") int customerId) {
		Customer customer = null;
		try {

			customer = custServiceiImpl.getCustomerById(customerId);
			custServiceiImpl.deleteCustomer(customer);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			logger.debug("Response {}",HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Response {}",HttpStatus.OK);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// Update the customer details
	@PutMapping("/update/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "customerId") int customerId,
			@RequestBody Customer customer) {
		logger.debug("Response {}",customer);
		try {
			Customer c1 = custServiceiImpl.getCustomerById(customerId);

			c1.setFirstName(customer.getFirstName());
			c1.setLastName(customer.getFirstName());
			c1.setPhone(customer.getPhone());
			c1.setemail(customer.getemail());
			c1.setAddress(customer.getAddress());
			c1.setPassword(customer.getPassword());

			Customer updatedCustomer = custServiceiImpl.updateCustomer(c1);
			logger.debug("Response {}",HttpStatus.OK);
			return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
		} catch (Exception e) {
			logger.debug("Response {}",HttpStatus.CONFLICT);
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

}
