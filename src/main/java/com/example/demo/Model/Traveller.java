package com.example.demo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "travellers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Traveller {

    @Id
    private String id;

    private String userId;   // reference to User

    private String name;
    private int age;
    private String gender;
}