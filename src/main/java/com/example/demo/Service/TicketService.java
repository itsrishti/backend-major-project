package com.example.demo.Service;

import com.example.demo.Model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getTicketsByBooking(String bookingId);

    Ticket getTicketById(String ticketId);

    Ticket scanTicket(String qrCode);
}