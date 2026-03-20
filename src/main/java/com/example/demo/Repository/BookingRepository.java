package com.example.demo.Repository;

import com.example.demo.Model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    // Get all PAID bookings for a museum on a specific date
    List<Booking> findByMuseumIdAndVisitDateAndStatus(
            String museumId,
            LocalDate visitDate,
            String status
    );
}