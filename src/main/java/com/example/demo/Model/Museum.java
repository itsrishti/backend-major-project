package com.example.demo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Museumdata")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Museum {

    @Id
    private String id;

    private String name;
    private String address;
    private String state;
    private String overview;
    private String theme;

    @Field("establishment_year")
    private int establishmentYear;

    private String website;

    @Field("contact_info")
    private String contactInfo;

    @Field("admission_fee")
    private String admissionFee;

    private String timings;
    private String facilities;

    private int dailyTicketCap;
}