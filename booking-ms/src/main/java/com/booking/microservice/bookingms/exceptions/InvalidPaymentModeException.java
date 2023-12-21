package com.booking.microservice.bookingms.exceptions;

/**
 * This exception is thrown when an invalid payment mode is encountered.
 */
public class InvalidPaymentModeException extends RuntimeException {

    /**
     * Constructs a new InvalidPaymentModeException with the specified detail
     * message.
     *
     * @param message the detail message
     */
    public InvalidPaymentModeException(String message) {
        super(message);
    }
}
