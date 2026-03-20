package com.example.demo.SeviceImp;

import com.example.demo.Model.TicketBookingPayment;
import com.example.demo.Repository.TicketBookingPaymentRepository;
import com.example.demo.Service.TicketBookingPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TicketBookingPaymentImpl implements TicketBookingPaymentService {

    private final TicketBookingPaymentRepository repository;

    private static final int DAILY_TICKET_CAP = 100;

    // ✅ STEP 1 - PREVIEW
    @Override
    public List<TicketBookingPayment> preview(TicketBookingPayment request) {

        if (request.getVisitDate() == null) {
            throw new RuntimeException("Visit date is required");
        }

        if (request.getTicketCount() <= 0) {
            throw new RuntimeException("Ticket count must be greater than 0");
        }

        LocalDateTime start = request.getVisitDate().atStartOfDay();
        LocalDateTime end = request.getVisitDate().atTime(23, 59, 59);

        List<TicketBookingPayment> existing =
                repository.findByMuseumIdAndCreatedAtBetween(
                        request.getMuseumId(),
                        start,
                        end
                );

        // 🔥 Count actual tickets (not records)
        int alreadyBooked = existing.stream()
                .mapToInt(TicketBookingPayment::getTicketCount)
                .sum();

        if (alreadyBooked + request.getTicketCount() > DAILY_TICKET_CAP) {
            throw new RuntimeException(
                    "Only " + (DAILY_TICKET_CAP - alreadyBooked) + " tickets left"
            );
        }

        String bookingId = UUID.randomUUID().toString();

        List<TicketBookingPayment> tickets = new ArrayList<>();

        for (int i = 1; i <= request.getTicketCount(); i++) {

            TicketBookingPayment ticket = TicketBookingPayment.builder()
                    .bookingId(bookingId)
                    .userId(request.getUserId())
                    .museumId(request.getMuseumId())
                    .visitDate(request.getVisitDate())
                    .ticketCount(request.getTicketCount())
                    .totalAmount(request.getTotalAmount())
                    .ticketNumber(i)
                    .price(request.getTotalAmount() / request.getTicketCount())
                    .status("PREVIEW")
                    .qrCode(null)
                    .used(false)
                    .createdAt(LocalDateTime.now())
                    .build();

            tickets.add(repository.save(ticket)); // saving preview
        }

        return tickets;
    }

    // ✅ STEP 2 - PAYMENT
    @Override
    public List<TicketBookingPayment> makePayment(String bookingId) {

        List<TicketBookingPayment> tickets = repository.findByBookingId(bookingId);

        if (tickets.isEmpty()) {
            throw new RuntimeException("Booking not found");
        }

        for (TicketBookingPayment ticket : tickets) {

            ticket.setStatus("PAID");

            String qr = "QR_" + ticket.getBookingId() + "_" + ticket.getTicketNumber();
            ticket.setQrCode(qr);
        }

        return repository.saveAll(tickets);
    }

    // ✅ STEP 3
    @Override
    public List<TicketBookingPayment> getTicketsByBooking(String bookingId) {

        List<TicketBookingPayment> tickets = repository.findByBookingId(bookingId);

        if (tickets.isEmpty()) {
            throw new RuntimeException("Tickets not found");
        }

        return tickets;
    }

    // ✅ STEP 4 - SCAN
    @Override
    public TicketBookingPayment scanTicket(String qrCode) {

        TicketBookingPayment ticket = repository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Invalid QR Code"));

        if (!"PAID".equals(ticket.getStatus())) {
            throw new RuntimeException("Ticket not paid");
        }

        if (Boolean.TRUE.equals(ticket.getUsed())) {
            throw new RuntimeException("Ticket already used");
        }

        ticket.setUsed(true);

        return repository.save(ticket);
    }
}