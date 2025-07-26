package com.payments.analyzer.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "interest_credit")
@Data
public class InterestCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Temporal(TemporalType.DATE)
    private Date interestDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;
}

