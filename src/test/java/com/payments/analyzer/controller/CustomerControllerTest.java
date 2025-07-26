package com.payments.analyzer.controller;

import com.payments.analyzer.entity.Customer;
import com.payments.analyzer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

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

    @Test
    void testGetAllCustomers() {
        when(customerService.getAllCustomers()).thenReturn(List.of(customer));

        List<Customer> result = customerController.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void testGetCustomerById() {
        when(customerService.getCustomerById(1L)).thenReturn(customer);

        Customer result = customerController.getCustomerById(1L);

        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void testCreateCustomer() {
        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

        Customer result = customerController.createCustomer(customer);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testUpdateCustomer() {
        when(customerService.updateCustomer(eq(1L), any(Customer.class))).thenReturn(customer);

        Customer result = customerController.updateCustomer(1L, customer);

        assertNotNull(result);
        assertEquals(1L, result.getCustomerId());
    }

    @Test
    void testDeleteCustomer() {
        doNothing().when(customerService).deleteCustomer(1L);

        assertDoesNotThrow(() -> customerController.deleteCustomer(1L));
        verify(customerService, times(1)).deleteCustomer(1L);
    }
}
