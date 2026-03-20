package com.example.demo.SeviceImp;

import com.example.demo.Model.Booking;
import com.example.demo.Model.Ticket;
import com.example.demo.Model.Traveller;
import com.example.demo.Repository.BookingRepository;
import com.example.demo.Repository.TicketRepository;
import com.example.demo.Repository.TravellerRepository;
import com.example.demo.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TravellerRepository travellerRepository;
    private final TicketRepository ticketRepository;

    private static final int MAX_TICKETS_PER_DAY = 100;

    // CREATE BOOKING
    @Override
    public Booking createBooking(Booking booking) {

        List<Traveller> travellers = travellerRepository.findAllById(booking.getTravellerIds());

        int requestedTickets = travellers.size();

        // 🔥 CAP CHECK (ONLY PAID BOOKINGS COUNT)
        List<Booking> paidBookings = bookingRepository
                .findByMuseumIdAndVisitDateAndStatus(
                        booking.getMuseumId(),
                        booking.getVisitDate(),
                        "PAID"
                );

        int alreadyBooked = paidBookings.stream()
                .mapToInt(b -> b.getTravellerIds().size())
                .sum();

        if (alreadyBooked + requestedTickets > MAX_TICKETS_PER_DAY) {
            throw new RuntimeException("Daily ticket limit exceeded for this museum!");
        }

        // SET DATA
        booking.setTotalAmount(requestedTickets * booking.getPricePerTicket());
        booking.setStatus("PENDING"); // better than CREATED
        booking.setCreatedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    // MAKE PAYMENT
    @Override
    public Booking makePayment(String bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if ("PAID".equals(booking.getStatus())) {
            throw new RuntimeException("Already paid");
        }

        List<Traveller> travellers = travellerRepository.findAllById(booking.getTravellerIds());

        int requestedTickets = travellers.size();

        // 🔥 RE-CHECK CAP (VERY IMPORTANT)
        List<Booking> paidBookings = bookingRepository
                .findByMuseumIdAndVisitDateAndStatus(
                        booking.getMuseumId(),
                        booking.getVisitDate(),
                        "PAID"
                );

        int alreadyBooked = paidBookings.stream()
                .mapToInt(b -> b.getTravellerIds().size())
                .sum();

        if (alreadyBooked + requestedTickets > MAX_TICKETS_PER_DAY) {
            throw new RuntimeException("Tickets sold out!");
        }

        // 🎟️ CREATE TICKETS
        for (Traveller t : travellers) {

            Ticket ticket = Ticket.builder()
                    .bookingId(booking.getId())
                    .travellerId(t.getId())
                    .travellerName(t.getName())
                    .age(t.getAge())
                    .gender(t.getGender())
                    .qrCode("QR_" + booking.getId() + "_" + t.getId())
                    .used(false)
                    .build();

            ticketRepository.save(ticket);
        }

        // UPDATE STATUS
        booking.setStatus("PAID");

        return bookingRepository.save(booking);
    }

    // GET BOOKING
    @Override
    public Booking getBooking(String bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
}