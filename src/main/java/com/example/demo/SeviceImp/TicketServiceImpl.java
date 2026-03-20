package com.example.demo.SeviceImp;

import com.example.demo.Model.Ticket;
import com.example.demo.Repository.TicketRepository;
import com.example.demo.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> getTicketsByBooking(String bookingId) {
        return ticketRepository.findByBookingId(bookingId);
    }

    @Override
    public Ticket getTicketById(String ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public Ticket scanTicket(String qrCode) {

        Ticket ticket = ticketRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Invalid QR"));

        if (Boolean.TRUE.equals(ticket.getUsed())) {
            throw new RuntimeException("Ticket already used");
        }

        ticket.setUsed(true);

        return ticketRepository.save(ticket);
    }
}