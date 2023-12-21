package com.booking.microservice.payment.service;

import org.springframework.stereotype.Service;

import com.booking.microservice.payment.dto.PaymentRequest;
import com.booking.microservice.payment.entity.TransactionDetailsEntity;
import com.booking.microservice.payment.exceptions.InvalidBookingIdException;
import com.booking.microservice.payment.exceptions.InvalidPaymentModelException;
import com.booking.microservice.payment.exceptions.TransactionNotFoundException;
import com.booking.microservice.payment.repository.TransactionRepository;

import java.util.Date;

/**
 * The PaymentService class is responsible for making payments based on the
 * provided PaymentRequest.
 * It interacts with the TransactionRepository to save transaction details.
 */
@Service
public class PaymentService {

    private final TransactionRepository transactionRepository;

    /**
     * Constructs a new PaymentService with the specified TransactionRepository.
     *
     * @param transactionRepository the repository for transaction details
     */
    public PaymentService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Makes a payment based on the provided PaymentRequest.
     *
     * @param paymentRequest the payment request containing booking and payment
     *                       details
     * @return the transaction ID of the saved transaction
     * @throws InvalidBookingIdException    if the booking ID is invalid
     * @throws InvalidPaymentModelException if the payment mode is invalid
     */
    public int makePayment(PaymentRequest paymentRequest) {
        if (paymentRequest.getBookingId() <= 0) {
            throw new InvalidBookingIdException("Invalid Booking Id");
        }

        

        if (!isValidPaymentMode(paymentRequest.getPaymentMode())) {
            throw new InvalidPaymentModelException("Invalid mode of payment");
        }

        TransactionDetailsEntity transactionDetails = new TransactionDetailsEntity();
        transactionDetails.setPaymentMode(paymentRequest.getPaymentMode());
        transactionDetails.setBookingId(paymentRequest.getBookingId());
        transactionDetails.setUpiId(paymentRequest.getUpiId());
        transactionDetails.setCardNumber(paymentRequest.getCardNumber());
        transactionDetails.setTransactionDate(new Date());

        TransactionDetailsEntity savedTransaction = transactionRepository.save(transactionDetails);
        return savedTransaction.getTransactionId();
    }

    /**
     * Gets the transaction details for the given transaction ID.
     *
     * @param transactionId the transaction ID for which the details are to be
     *                      fetched
     * @return the transaction details
     * @throws InvalidBookingIdException    if the booking ID is invalid
     * @throws InvalidPaymentModelException if the payment mode is invalid
     */

    public TransactionDetailsEntity getTransactionDetails(int transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found for ID: " + transactionId));
    }

    /**
     * Checks if the given payment mode is valid.
     *
     * @param paymentMode the payment mode to be checked
     * @return true if the payment mode is valid, false otherwise
     */
    private boolean isValidPaymentMode(String paymentMode) {
        return "UPI".equalsIgnoreCase(paymentMode) || "CARD".equalsIgnoreCase(paymentMode);
    }
}
