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
    private String id;

    private String bookingId;
    private String travellerId;

    // SNAPSHOT DATA
    private String travellerName;
    private int age;
    private String gender;

    private String qrCode;

    private Boolean used;
}