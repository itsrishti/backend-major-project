package com.example.demo.Controller;

import com.example.demo.Model.Ticket;
import com.example.demo.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
@CrossOrigin
public class TicketController {

    private final TicketService ticketService;

    // GET ALL TICKETS BY BOOKING
    @GetMapping("/booking/{bookingId}")
    public List<Ticket> getTickets(@PathVariable String bookingId) {
        return ticketService.getTicketsByBooking(bookingId);
    }

    // GET SINGLE TICKET
    @GetMapping("/{ticketId}")
    public Ticket getTicket(@PathVariable String ticketId) {
        return ticketService.getTicketById(ticketId);
    }

    // SCAN
    @GetMapping("/scan")
    public Ticket scan(@RequestParam String qrCode) {
        return ticketService.scanTicket(qrCode);
    }
}
