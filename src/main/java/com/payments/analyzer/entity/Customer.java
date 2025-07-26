package com.payments.analyzer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(length = 100, nullable = false)
    private String name;


    @Column(length = 100, nullable = false, unique = true)
    @Email(message = "Invalid email format")
    @NotBlank
    private String email;

    @Column(length = 15)
    private String phone;

    private Boolean registered;
}

