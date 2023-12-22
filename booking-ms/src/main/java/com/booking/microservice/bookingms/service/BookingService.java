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
    // private final PaymentService paymentService;

    public BookingService(BookingRepository bookingRepository/* , PaymentService paymentService */) {
        this.bookingRepository = bookingRepository;
        // this.paymentService = paymentService;
    }

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

    public Iterable<BookingInfoEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingInfoEntity getBookingById(Integer bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    private String generateRandomRoomNumbers() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100) + 1);
    }

    private int calculateRoomPrice(int numOfRooms) {
        return 1000 * numOfRooms; // Replace with your logic
    }

    private boolean isValidPaymentMode(String paymentMode) {
        return "UPI".equalsIgnoreCase(paymentMode) || "CARD".equalsIgnoreCase(paymentMode);
    }

    public String getServiceUrl(String serviceName) {
        System.out.println("Getting service URL for " + serviceName);
        System.out.println("All services " + discoveryClient.getServices().toString());
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (instances != null && !instances.isEmpty()) {
            return instances.get(0).getUri().toString();
        }
        return null;
    }

    public TransactionDetailsEntity getTransactionDetails(Integer bookingId) {
        String bookingServiceUrl = getServiceUrl("payment-service");
        System.out.println("Booking service URL " + bookingServiceUrl + "/bookings/" + bookingId + "/transaction");
        if (bookingServiceUrl != null) {
            return restTemplate.getForObject(bookingServiceUrl + "/bookings/" + bookingId + "/transaction",
                    TransactionDetailsEntity.class);
        }
        return null;
    }

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
