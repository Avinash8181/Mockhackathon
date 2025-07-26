package com.payments.analyzer.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payments.analyzer.entity.Customer;
import com.payments.analyzer.exception.ResourceNotFoundException;
import com.payments.analyzer.repository.CustomerRepository;
import com.payments.analyzer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        try {
            logger.info("Fetching all customers");
            return customerRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching customers: {}", e.getMessage(), e);
            throw new RuntimeException("Unable to fetch customers");
        }
    }

    @Override
    public Customer getCustomerById(Long id) {
        try {
            logger.info("Fetching customer with ID: {}", id);
            return customerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        } catch (ResourceNotFoundException e) {
            logger.warn("Customer not found: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error fetching customer: {}", e.getMessage(), e);
            throw new RuntimeException("Unable to fetch customer");
        }
    }

    @Override
    public Customer createCustomer(Customer customer) {
        try {
            logger.info("Creating new customer: {}", customer);
            return customerRepository.save(customer);
        } catch (Exception e) {
            logger.error("Error creating customer: {}", e.getMessage(), e);
            throw new RuntimeException("Unable to create customer");
        }
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        try {
            logger.info("Updating customer with ID: {}", id);
            if (!customerRepository.existsById(id)) {
                throw new ResourceNotFoundException("Customer not found with ID: " + id);
            }
            customer.setCustomerId(id);
            return customerRepository.save(customer);
        } catch (ResourceNotFoundException e) {
            logger.warn("Customer not found: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error updating customer: {}", e.getMessage(), e);
            throw new RuntimeException("Unable to update customer");
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        try {
            logger.info("Deleting customer with ID: {}", id);
            if (!customerRepository.existsById(id)) {
                throw new ResourceNotFoundException("Customer not found with ID: " + id);
            }
            customerRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            logger.warn("Customer not found: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error deleting customer: {}", e.getMessage(), e);
            throw new RuntimeException("Unable to delete customer");
        }
    }
}
