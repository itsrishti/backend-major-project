package com.example.demo.Controller;

import com.example.demo.Model.TicketBookingPayment;
import com.example.demo.Service.TicketBookingPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-payment")
@RequiredArgsConstructor
@CrossOrigin
public class TicketBookingPaymentController {

    private final TicketBookingPaymentService service;

    // STEP 1
    @PostMapping("/preview")
    public List<TicketBookingPayment> preview(@RequestBody TicketBookingPayment request) {
        return service.preview(request);
    }

    // STEP 2
    @PostMapping("/pay/{bookingId}")
    public List<TicketBookingPayment> pay(@PathVariable String bookingId) {
        return service.makePayment(bookingId);
    }

    // STEP 3
    @GetMapping("/tickets/{bookingId}")
    public List<TicketBookingPayment> getTickets(@PathVariable String bookingId) {
        return service.getTicketsByBooking(bookingId);
    }

    // STEP 4
    @GetMapping("/scan")
    public TicketBookingPayment scan(@RequestParam String qrCode) {
        return service.scanTicket(qrCode);
    }
}