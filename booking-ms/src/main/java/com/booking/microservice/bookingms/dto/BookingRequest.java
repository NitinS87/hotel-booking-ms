package com.booking.microservice.bookingms.dto;

import java.sql.Date;

import lombok.Data;

/**
 * Represents a booking request for a hotel room.
 */
@Data
public class BookingRequest {
    /**
     * The start date of the booking.
     */
    private Date fromDate;

    /**
     * The end date of the booking.
     */
    private Date toDate;

    /**
     * The Aadhar number of the person making the booking.
     */
    private String aadharNumber;

    /**
     * The number of rooms requested for the booking.
     */
    private int numOfRooms;
}
