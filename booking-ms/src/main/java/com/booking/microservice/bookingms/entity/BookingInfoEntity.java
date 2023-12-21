package com.booking.microservice.bookingms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
public class BookingInfoEntity {

    /**
     * Represents the unique identifier of the booking.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Represents the start date of the booking.
     */
    @Column(nullable = false)
    private Date fromDate;

    /**
     * Represents the end date of the booking.
     */
    @Column(nullable = false)
    private Date toDate;

    /**
     * Represents the Aadhar number of the person making the booking.
     */
    @Column(nullable = false)
    private String aadharNumber;

    /**
     * Represents the number of rooms booked.
     */
    @Column(nullable = false)
    private int numOfRooms;

    /**
     * Represents the room numbers of the booked rooms.
     */
    @Column(nullable = false)
    private String roomNumbers;

    /**
     * Represents the price of each room.
     */
    @Column(nullable = false)
    private int roomPrice;

    /**
     * Represents the transaction ID associated with the booking.
     */
    @Column(nullable = false)
    private int transactionId = 0;

    /**
     * Represents the date when the booking was made.
     */
    @Column(nullable = false)
    private Date bookedOn;
}
