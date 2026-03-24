package com.example.demo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "testingdata")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Museum {

    @Id
    private String id;

    private String name;
    private Address address;

    private String overview;
    private List<String> themes;

    private Integer yearOfEstablishment;
    private String website;

    private ContactInfo contactInfo;

    private List<TicketPrice> ticketPrices;
    private List<OperatingHour> operatingHours;

    private List<String> facilities;

    private Date createdAt;
    private Date updatedAt;
}





