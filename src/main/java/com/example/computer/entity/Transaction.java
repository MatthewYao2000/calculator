package com.example.computer.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "TRANSACTION")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Transaction {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "TRANSACTION_DATE")
    private Timestamp transactionDate;

    @Column(name = "AMOUNT")
    private double transactionAmount;

}
