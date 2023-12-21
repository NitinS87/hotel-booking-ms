package com.booking.microservice.bookingms.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.booking.microservice.bookingms.exceptions.InvalidBookingIdException;
import com.booking.microservice.bookingms.exceptions.InvalidPaymentModeException;

/**
 * This class is a controller advice that handles custom exceptions thrown by
 * the application.
 * It provides exception handling methods for specific exceptions as well as a
 * generic exception handler.
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handles the InvalidBookingIdException and returns a ResponseEntity with a
     * JSON response containing
     * the exception message and a status code of 400 (Bad Request).
     *
     * @param ex The InvalidBookingIdException to be handled.
     * @return A ResponseEntity with a JSON response and a status code of 400.
     */
    @ExceptionHandler(InvalidBookingIdException.class)
    public ResponseEntity<String> handleInvalidBookingIdException(InvalidBookingIdException ex) {
        return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 400}",
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the InvalidPaymentModeException and returns a ResponseEntity with a
     * JSON response containing
     * the exception message and a status code of 400 (Bad Request).
     *
     * @param ex The InvalidPaymentModeException to be handled.
     * @return A ResponseEntity with a JSON response and a status code of 400.
     */
    @ExceptionHandler(InvalidPaymentModeException.class)
    public ResponseEntity<String> handleInvalidPaymentModeException(InvalidPaymentModeException ex) {
        return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 400}",
                HttpStatus.BAD_REQUEST);
    }

    // Add more exception handlers as needed

    /**
     * Handles any other exception that is not specifically handled by other
     * exception handlers.
     * Returns a ResponseEntity with a JSON response containing the exception
     * message and a status code of 500
     * (Internal Server Error).
     *
     * @param ex The Exception to be handled.
     * @return A ResponseEntity with a JSON response and a status code of 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 500}",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
