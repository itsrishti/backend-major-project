package com.example.demo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    private String id;

    private String userId;
    private String museumId;

    private List<String> travellerIds;

    private double pricePerTicket;
    private double totalAmount;

    private String status; // PENDING, PAID, CANCELLED

    private LocalDate visitDate;   // 🔥 REQUIRED for ticket cap logic
    private LocalDateTime createdAt;
}