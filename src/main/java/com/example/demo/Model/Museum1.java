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
public class Museum1 {

    @Id
    private String id;

    private String name;
    private Address address;

    private String overview;
    private List<String> themes;

    private String website;

    private ContactInfo contactInfo;

    private List<TicketPrice> ticketPrices;
    private List<OperatingHour> operatingHours;

    private List<String> facilities;

    private Date createdAt;
    private Date updatedAt;

    // 🔽 Nested Classes

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        private String street;
        private String state;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContactInfo {
        private List<String> phoneNumbers;
        private List<String> emails;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TicketPrice {
        private String category;
        private Double price;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OperatingHour {
        private List<String> daysOfWeek;
        private Date openTime;
        private Date closeTime;
        private Boolean isClosed;
        private String note;
    }
}