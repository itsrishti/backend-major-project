package com.example.demo.Repository;

import com.example.demo.Model.TicketBookingPayment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketBookingPaymentRepository
        extends MongoRepository<TicketBookingPayment, String> {

    Optional<TicketBookingPayment> findByQrCode(String qrCode);

    List<TicketBookingPayment> findByBookingId(String bookingId);

    List<TicketBookingPayment> findByMuseumIdAndCreatedAtBetween(
            String museumId,
            LocalDateTime start,
            LocalDateTime end
    );
}