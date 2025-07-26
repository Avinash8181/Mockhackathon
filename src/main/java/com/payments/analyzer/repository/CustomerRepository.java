package com.payments.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.payments.analyzer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
