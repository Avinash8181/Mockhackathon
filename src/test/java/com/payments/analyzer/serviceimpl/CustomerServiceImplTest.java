package com.payments.analyzer.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.payments.analyzer.entity.Customer;
import com.payments.analyzer.exception.ResourceNotFoundException;
import com.payments.analyzer.repository.CustomerRepository;

class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");
        customer.setPhone("1234567890");
        customer.setRegistered(true);
    }

    // ✅ Positive Test: Get all customers
    @Test
    void testGetAllCustomers_Positive() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));

        List<Customer> result = customerService.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    // ❌ Negative Test: Get all customers - Exception
    @Test
    void testGetAllCustomers_Exception() {
        when(customerRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> customerService.getAllCustomers());
        assertEquals("Unable to fetch customers. Please try again later.", ex.getMessage());
    }

    // ✅ Positive Test: Get customer by ID
    @Test
    void testGetCustomerById_Positive() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerById(1L);

        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
    }

    // ❌ Negative Test: Get customer by invalid ID
    @Test
    void testGetCustomerById_Negative() {
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> customerService.getCustomerById(999L));
        assertEquals("Customer not found with ID: 999", ex.getMessage());
    }

    // ✅ Positive Test: Create customer
    @Test
    void testCreateCustomer_Positive() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = customerService.createCustomer(customer);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    // ❌ Negative Test: Create customer - Exception
    @Test
    void testCreateCustomer_Exception() {
        when(customerRepository.save(any(Customer.class))).thenThrow(new RuntimeException("Insert failed"));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> customerService.createCustomer(customer));
        assertEquals("Customer already exists or input data is invalid.", ex.getMessage());
    }


    // ✅ Positive Test: Update customer
    @Test
    void testUpdateCustomer_Positive() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = customerService.updateCustomer(1L, customer);

        assertNotNull(result);
        assertEquals(1L, result.getCustomerId());
    }

    // ❌ Negative Test: Update customer - Not found
    @Test
    void testUpdateCustomer_NotFound() {
        when(customerRepository.existsById(999L)).thenReturn(false);

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> customerService.updateCustomer(999L, customer));
        assertEquals("Customer not found with ID: 999", ex.getMessage());
    }

    // ✅ Positive Test: Delete customer
    @Test
    void testDeleteCustomer_Positive() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        doNothing().when(customerRepository).deleteById(1L);

        assertDoesNotThrow(() -> customerService.deleteCustomer(1L));
        verify(customerRepository, times(1)).deleteById(1L);
    }

    // ❌ Negative Test: Delete customer - Not found
    @Test
    void testDeleteCustomer_NotFound() {
        when(customerRepository.existsById(999L)).thenReturn(false);

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> customerService.deleteCustomer(999L));
        assertEquals("Customer not found with ID: 999", ex.getMessage());
    }
}
