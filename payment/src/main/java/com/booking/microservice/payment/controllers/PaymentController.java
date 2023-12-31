package com.booking.microservice.payment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.booking.microservice.payment.dto.PaymentRequest;
import com.booking.microservice.payment.entity.TransactionDetailsEntity;
import com.booking.microservice.payment.exceptions.InvalidBookingIdException;
import com.booking.microservice.payment.exceptions.InvalidPaymentModelException;
import com.booking.microservice.payment.exceptions.TransactionNotFoundException;
import com.booking.microservice.payment.service.PaymentService;

/**
 * This class represents the controller for handling payment-related operations.
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Constructs a new PaymentController with the specified PaymentService.
     * 
     * @param paymentService the PaymentService to be used for payment operations
     */
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Handles the request for making a payment.
     * 
     * @param paymentRequest the PaymentRequest containing the payment details
     * @return a ResponseEntity with the result of the payment operation
     */
    @PostMapping("/transaction")
    public ResponseEntity<Integer> makePayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            int transactionId = paymentService.makePayment(paymentRequest);
            return new ResponseEntity<Integer>(transactionId, HttpStatus.OK);
        } catch (InvalidBookingIdException | InvalidPaymentModelException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    /**
     * Retrieves the details of a transaction based on the provided transaction ID.
     *
     * @param transactionId The ID of the transaction to retrieve.
     * @return The ResponseEntity containing the transaction details.
     * @throws TransactionNotFoundException If the transaction with the given ID is
     *                                      not found.
     */
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<TransactionDetailsEntity> getTransactionDetails(@PathVariable int transactionId) {
        try {
            TransactionDetailsEntity transactionDetails = paymentService.getTransactionDetails(transactionId);
            return new ResponseEntity<>(transactionDetails, HttpStatus.OK);
        } catch (TransactionNotFoundException ex) {
            throw new TransactionNotFoundException("Transaction not found");
        }
    }

    /**
     * Handles the exception for unexpected exceptions.
     * 
     * @param ex the Exception that occurred
     * @return a ResponseEntity with the error message and status code
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 500}",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
