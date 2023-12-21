package com.booking.microservice.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.microservice.payment.entity.TransactionDetailsEntity;

/**
 * This interface represents the repository for managing transaction details in
 * the payment microservice.
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetailsEntity, Integer> {
    // Add custom queries if needed
}
