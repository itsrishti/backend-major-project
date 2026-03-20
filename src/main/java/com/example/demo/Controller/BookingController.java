package com.example.demo.Controller;

import com.example.demo.Model.Booking;
import com.example.demo.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@CrossOrigin
public class BookingController {

    private final BookingService bookingService;

    // CREATE BOOKING
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(createdBooking);
    }

    // PAYMENT
    @PostMapping("/pay/{id}")
    public ResponseEntity<Booking> pay(@PathVariable String id) {
        Booking paidBooking = bookingService.makePayment(id);
        return ResponseEntity.ok(paidBooking);
    }

    // GET BOOKING
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable String id) {
        Booking booking = bookingService.getBooking(id);
        return ResponseEntity.ok(booking);
    }
}