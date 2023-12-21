package com.booking.microservice.payment.exceptions;

/**
 * Exception thrown when an invalid booking ID is encountered.
 */
public class InvalidBookingIdException extends RuntimeException {
    public InvalidBookingIdException(String message) {
        super(message);
    }
}
