package com.example.demo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private int establishmentYear;
    private String website;
    private String contactInfo;
    private String admissionFee;
    private String timings;
    private String facilities;
}
