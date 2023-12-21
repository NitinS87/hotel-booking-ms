package com.booking.microservice.payment.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.booking.microservice.payment.exceptions.InvalidBookingIdException;
import com.booking.microservice.payment.exceptions.InvalidPaymentModelException;

/**
 * This class is a controller advice that handles exceptions thrown by the
 * application.
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handles the InvalidBookingIdException and returns a ResponseEntity with a
     * JSON error message and status code 400.
     *
     * @param ex The InvalidBookingIdException that was thrown
     * @return A ResponseEntity containing the error message and status code
     */
    @ExceptionHandler(InvalidBookingIdException.class)
    public ResponseEntity<String> handleInvalidBookingIdException(InvalidBookingIdException ex) {
        return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 400}",
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the InvalidPaymentModelException and returns a ResponseEntity with a
     * JSON error message and status code 400.
     *
     * @param ex The InvalidPaymentModelException that was thrown
     * @return A ResponseEntity containing the error message and status code
     */
    @ExceptionHandler(InvalidPaymentModelException.class)
    public ResponseEntity<String> handleInvalidPaymentModeException(InvalidPaymentModelException ex) {
        return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 400}",
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles generic exceptions and returns a ResponseEntity with a JSON error
     * message and status code 500.
     *
     * @param ex The Exception that was thrown
     * @return A ResponseEntity containing the error message and status code
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 500}",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
