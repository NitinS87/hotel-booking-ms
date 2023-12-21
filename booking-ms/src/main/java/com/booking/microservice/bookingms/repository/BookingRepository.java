package com.booking.microservice.bookingms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.microservice.bookingms.entity.BookingInfoEntity;

/**
 * This interface represents the repository for managing booking information
 * entities.
 * It extends the JpaRepository interface, providing CRUD operations for the
 * BookingInfoEntity class.
 */
@Repository
public interface BookingRepository extends JpaRepository<BookingInfoEntity, Integer> {
}