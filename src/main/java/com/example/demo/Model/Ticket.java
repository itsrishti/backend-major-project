package com.example.demo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    private String id;   // simpler than ticketId

    private String bookingId;  // 🔥 relation added

    private double cost;
    private int age;
    private String nationality;

    private boolean student;   // remove "is" prefix

    private String ticketType; // GENERAL / STUDENT
    private String qrCodeData;
}
