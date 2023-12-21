package com.booking.microservice.bookingms.exceptions;

/**
 * Exception thrown when an invalid booking ID is encountered.
 * This exception is typically thrown when attempting to perform operations on a booking with an invalid ID.
 * An invalid booking ID could be a non-existent ID or a format that does not match the expected format.
 * 
 * @see RuntimeException
 */
/**
 * This exception is thrown when an invalid booking ID is encountered.
 */
public class InvalidBookingIdException extends RuntimeException {

    /**
     * Constructs a new InvalidBookingIdException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidBookingIdException(String message) {
        super(message);
    }
}
