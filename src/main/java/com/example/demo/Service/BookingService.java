package com.example.demo.Service;

import com.example.demo.Model.Booking;

public interface BookingService {

    Booking createBooking(Booking booking);

    Booking makePayment(String bookingId);

    Booking getBooking(String bookingId);
}