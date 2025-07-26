package com.payments.analyzer.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Long accountId;
    private Long customerId;
    private BigDecimal balance;
}

