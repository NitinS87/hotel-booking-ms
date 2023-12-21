package com.booking.microservice.bookingms.dto;

import lombok.Data;

/**
 * The Jakarta Persistence API provides a set of Java classes and interfaces that define the Java Persistence specification.
 * It is used for managing relational data in Java applications.
 * The @Entity annotation is used to specify that a class is an entity and is mapped to a database table.
 * The @Table annotation is used to specify the details of the table that will be used to persist the entity in the database.
 * The @Column annotation is used to specify the details of the column that will be used to persist the attribute in the database.
 * The @Id annotation is used to specify the primary key of an entity.
 * The @GeneratedValue annotation is used to specify the generation strategy for the primary key.
 */
import jakarta.persistence.*;
import java.util.Date;

/**
 * Represents the details of a transaction.
 */
@Entity
@Table(name = "transaction")
@Data
public class TransactionDetailsEntity {

    /**
     * Represents the unique identifier for a transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    /**
     * Represents the payment mode used for the transaction.
     */
    @Column(nullable = false)
    private String paymentMode;

    /**
     * Represents the booking associated with the transaction.
     */
    @Column(nullable = false)
    private int bookingId;

    /**
     * Represents the UPI ID used for the transaction.
     */
    private String upiId;

    /**
     * Represents the card number used for the transaction.
     */
    private String cardNumber;

    /**
     * Represents the date and time when the transaction occurred.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
}
