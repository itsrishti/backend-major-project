package com.example.demo.Repository;

import com.example.demo.Model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends MongoRepository<Ticket, String> {

    List<Ticket> findByBookingId(String bookingId);

    Optional<Ticket> findByQrCode(String qrCode);
}