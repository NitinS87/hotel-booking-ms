package com.booking.microservice.bookingms.dto;

import lombok.Data;

/**
 * Represents a payment request for a hotel room booking.
 */
@Data
public class PaymentRequest {
    private String paymentMode;
    private int bookingId;
    private String upiId;
    private String cardNumber;
}
