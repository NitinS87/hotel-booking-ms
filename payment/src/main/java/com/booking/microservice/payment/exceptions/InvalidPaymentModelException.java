package com.booking.microservice.payment.exceptions;

/**
 * Exception thrown when an invalid payment model is encountered.
 */
public class InvalidPaymentModelException extends RuntimeException {
    /**
     * Constructs a new InvalidPaymentModelException with the specified detail
     * message.
     *
     * @param message the detail message
     */
    public InvalidPaymentModelException(String message) {
        super(message);
    }
}
