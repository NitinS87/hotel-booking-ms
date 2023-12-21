package com.booking.microservice.bookingms.dto;

import lombok.Data;

/**
 * Represents a payment request.
 */
@Data
public class PaymentRequest {
    private String paymentMode;
    private String upiId;
    private String cardNumber;
}
