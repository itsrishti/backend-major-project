package com.example.demo.Service;

import com.example.demo.Model.TicketBookingPayment;

import java.util.List;

public interface TicketBookingPaymentService {

    List<TicketBookingPayment> preview(TicketBookingPayment request);

    List<TicketBookingPayment> makePayment(String bookingId);

    List<TicketBookingPayment> getTicketsByBooking(String bookingId);

    TicketBookingPayment scanTicket(String qrCode);
}