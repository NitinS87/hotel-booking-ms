package com.booking.microservice.bookingms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.booking.microservice.bookingms.dto.BookingRequest;
import com.booking.microservice.bookingms.dto.PaymentRequest;
import com.booking.microservice.bookingms.dto.TransactionDetailsEntity;
import com.booking.microservice.bookingms.entity.BookingInfoEntity;
import com.booking.microservice.bookingms.exceptions.InvalidBookingIdException;
import com.booking.microservice.bookingms.exceptions.InvalidPaymentModeException;
import com.booking.microservice.bookingms.repository.BookingRepository;

import java.util.*;

/**
 * The BookingService class represents the Booking Service, which is responsible
 * for handling booking-related operations.
 * It provides methods to save a booking, make a payment, retrieve bookings, and
 * update bookings.
 */
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Saves a booking based on the provided booking request.
     *
     * @param bookingRequest The booking request containing the necessary
     *                       information.
     * @return The saved booking information entity.
     */
    public BookingInfoEntity saveBooking(BookingRequest bookingRequest) {
        String roomNumbers = "";
        for (int i = 0; i < bookingRequest.getNumOfRooms(); i++) {
            roomNumbers += generateRandomRoomNumbers() + ",";
        }
        roomNumbers = roomNumbers.substring(0, roomNumbers.length() - 1); // Remove last comma
        int roomPrice = calculateRoomPrice(bookingRequest.getNumOfRooms());

        BookingInfoEntity bookingInfo = new BookingInfoEntity();
        bookingInfo.setFromDate(bookingRequest.getFromDate());
        bookingInfo.setToDate(bookingRequest.getToDate());
        bookingInfo.setAadharNumber(bookingRequest.getAadharNumber());
        bookingInfo.setNumOfRooms(bookingRequest.getNumOfRooms());
        bookingInfo.setRoomNumbers(roomNumbers);
        bookingInfo.setRoomPrice(roomPrice);
        bookingInfo.setTransactionId(0);
        bookingInfo.setBookedOn(new Date());

        return bookingRepository.save(bookingInfo);
    }

    /**
     * Makes a payment for a booking and returns the updated BookingInfoEntity.
     *
     * @param bookingId      The ID of the booking.
     * @param paymentRequest The payment request containing the payment details.
     * @return The updated BookingInfoEntity after the payment is made.
     * @throws InvalidBookingIdException   If the booking ID is invalid.
     * @throws InvalidPaymentModeException If the payment mode is invalid.
     */
    public BookingInfoEntity makePayment(Integer bookingId, PaymentRequest paymentRequest) {
        BookingInfoEntity bookingInfo = getBookingById(bookingId);

        if (bookingInfo == null) {
            throw new InvalidBookingIdException("Invalid Booking Id");
        }

        if (!isValidPaymentMode(paymentRequest.getPaymentMode())) {
            throw new InvalidPaymentModeException("Invalid mode of payment");
        }

        paymentRequest.setBookingId(bookingId);
        int transactionId = 0;
        Integer transactionDetails = makeTransaction(paymentRequest);
        if (transactionDetails != null) {
            transactionId = transactionDetails;
        }

        String message = "Booking confirmed for user with aadhaar number: "
                + bookingInfo.getAadharNumber()
                + " | "
                + "Here are the booking details: " + bookingInfo.toString();
        System.out.println(message);

        bookingInfo.setTransactionId(transactionId);
        return bookingRepository.save(bookingInfo);
    }

    /**
     * Retrieves all bookings from the database.
     *
     * @return an Iterable of BookingInfoEntity containing all bookings
     */
    public Iterable<BookingInfoEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Retrieves a booking entity by its ID.
     *
     * @param bookingId the ID of the booking entity to retrieve
     * @return the booking entity with the specified ID, or null if not found
     */
    public BookingInfoEntity getBookingById(Integer bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    /**
     * Generates a random room number.
     *
     * @return a randomly generated room number as a String.
     */
    private String generateRandomRoomNumbers() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100) + 1);
    }

    /**
     * Calculates the total price for a given number of rooms.
     *
     * @param numOfRooms the number of rooms to calculate the price for
     * @return the total price for the given number of rooms
     */
    private int calculateRoomPrice(int numOfRooms) {
        return 1000 * numOfRooms; // Replace with your logic
    }

    /**
     * Checks if the given payment mode is valid.
     * Valid payment modes are "UPI" and "CARD" (case-insensitive).
     *
     * @param paymentMode the payment mode to be checked
     * @return true if the payment mode is valid, false otherwise
     */
    private boolean isValidPaymentMode(String paymentMode) {
        return "UPI".equalsIgnoreCase(paymentMode) || "CARD".equalsIgnoreCase(paymentMode);
    }

    /**
     * Retrieves the service URL for the specified service name.
     *
     * @param serviceName the name of the service
     * @return the service URL as a string, or null if no instances of the service
     *         are found
     */
    public String getServiceUrl(String serviceName) {
        System.out.println("Getting service URL for " + serviceName);
        System.out.println("All services " + discoveryClient.getServices().toString());
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (instances != null && !instances.isEmpty()) {
            return instances.get(0).getUri().toString();
        }
        return null;
    }

    /**
     * Retrieves the transaction details for a given booking ID.
     *
     * @param bookingId the ID of the booking
     * @return the transaction details entity associated with the booking ID, or
     *         null if not found
     */
    public TransactionDetailsEntity getTransactionDetails(Integer bookingId) {
        String bookingServiceUrl = getServiceUrl("payment-service");
        System.out.println("Booking service URL " + bookingServiceUrl + "/bookings/" + bookingId + "/transaction");
        if (bookingServiceUrl != null) {
            return restTemplate.getForObject(bookingServiceUrl + "/bookings/" + bookingId + "/transaction",
                    TransactionDetailsEntity.class);
        }
        return null;
    }

    /**
     * Makes a transaction by sending a payment request to the payment service.
     * 
     * @param paymentRequest the payment request object containing payment details
     * @return the transaction ID as an Integer
     */
    public Integer makeTransaction(PaymentRequest paymentRequest) {
        String bookingServiceUrl = getServiceUrl("payment-service");
        System.out.println("Booking service URL " + bookingServiceUrl + "/payment/transaction");
        if (bookingServiceUrl != null) {
            return restTemplate.postForObject(bookingServiceUrl + "/payment/transaction",
                    paymentRequest, Integer.class);
        }
        return 0;
    }
}
