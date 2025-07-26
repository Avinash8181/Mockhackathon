package com.payments.analyzer.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long customerId;
    private String name;
    private String email;
    private String phone;
    private Boolean registered;
}

