package com.payments.analyzer.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "sender_account_id", nullable = false)
    private Account senderAccount;

    @Column(length = 20)
    private String receiverAccountNumber;

    @Column(precision = 15, scale = 2)
    private BigDecimal amount;

    @Lob
    private String remarks;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Temporal(TemporalType.DATE)
    private Date scheduledDate;

    @Enumerated(EnumType.STRING)
    private RecurrenceFrequency recurrenceFrequency;

    private Integer recurrenceCount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private Timestamp createdAt;

    public enum PaymentMode {
        CHEQUE, INTERNET_BANKING, ECS
    }

    public enum RecurrenceFrequency {
        DAILY, MONTHLY
    }

    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED, CANCELLED
    }
}

