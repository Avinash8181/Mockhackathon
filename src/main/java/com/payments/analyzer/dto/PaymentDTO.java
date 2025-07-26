package com.payments.analyzer.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class PaymentDTO {
    private Long paymentId;
    private Long senderAccountId;
    private String receiverAccountNumber;
    private BigDecimal amount;
    private String remarks;
    private String paymentMode; // CHEQUE, INTERNET_BANKING, ECS, etc.
    private Date scheduledDate;
    private String recurrenceFrequency; // DAILY, MONTHLY
    private Integer recurrenceCount;
    private String status;
    private Timestamp createdAt;
}

