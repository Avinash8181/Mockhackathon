package com.payments.analyzer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.payments.analyzer.entity.Customer;
import com.payments.analyzer.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        logger.info("Received request to fetch all customers");
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        logger.info("Received request to fetch customer with ID: {}", id);
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        logger.info("Received request to create customer: {}", customer);
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customer) {
        logger.info("Received request to update customer with ID: {}", id);
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        logger.info("Received request to delete customer with ID: {}", id);
        customerService.deleteCustomer(id);
    }
}
