package com.booking.microservice.bookingms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.booking.microservice.bookingms.dto.BookingRequest;
import com.booking.microservice.bookingms.dto.PaymentRequest;
import com.booking.microservice.bookingms.entity.BookingInfoEntity;
import com.booking.microservice.bookingms.exceptions.InvalidBookingIdException;
import com.booking.microservice.bookingms.exceptions.InvalidPaymentModeException;
import com.booking.microservice.bookingms.service.BookingService;

/**
 * The BookingController class handles HTTP requests related to bookings.
 */
@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    /**
     * Constructs a new BookingController with the specified BookingService.
     *
     * @param bookingService the BookingService used to handle booking-related
     *                       operations
     */
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Retrieves a booking by its ID.
     *
     * @param bookingId the ID of the booking to retrieve
     * @return the ResponseEntity containing the BookingInfoEntity if found, or an
     *         error message if not found
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingInfoEntity> getBookingById(@PathVariable Integer bookingId) {
        BookingInfoEntity bookingInfo = bookingService.getBookingById(bookingId);

        if (bookingInfo == null) {
            throw new InvalidBookingIdException("Invalid Booking Id");
        }

        return new ResponseEntity<>(bookingInfo, HttpStatus.OK);
    }

    /**
     * Retrieves all bookings.
     *
     * @return the ResponseEntity containing the Iterable of BookingInfoEntity
     *         objects
     */
    @GetMapping
    public ResponseEntity<Iterable<BookingInfoEntity>> getAllBookings() {
        Iterable<BookingInfoEntity> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    /**
     * Creates a new booking.
     *
     * @param bookingRequest the BookingRequest object containing the details of the
     *                       booking to create
     * @return the ResponseEntity containing the created BookingInfoEntity
     */
    @PostMapping
    public ResponseEntity<BookingInfoEntity> createBooking(@RequestBody BookingRequest bookingRequest) {
        BookingInfoEntity savedBooking = bookingService.saveBooking(bookingRequest);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    /**
     * Makes a payment for a booking.
     *
     * @param bookingId      the ID of the booking to make the payment for
     * @param paymentRequest the PaymentRequest object containing the payment
     *                       details
     * @return the ResponseEntity containing a success message if the transaction is
     *         successful, or an error message if not
     */
    @PostMapping("/{bookingId}/transaction")
    public ResponseEntity<BookingInfoEntity> makePayment(@PathVariable Integer bookingId,
            @RequestBody PaymentRequest paymentRequest) {
        try {
            System.out.println("Booking Id: " + bookingId + ", Payment Request: " + paymentRequest);
            BookingInfoEntity bookingInfoEntity = bookingService.makePayment(bookingId, paymentRequest);
            return new ResponseEntity<>(bookingInfoEntity, HttpStatus.OK);
        } catch (InvalidBookingIdException | InvalidPaymentModeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    // Other endpoints, if any

    /**
     * Handles unexpected exceptions.
     *
     * @param ex the Exception that occurred
     * @return the ResponseEntity containing an error message and status code 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 500}",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
