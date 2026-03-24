package com.example.demo.Model;

import lombok.Data;

@Data
public class TicketPrice {
    private String category;
    private Integer amount;
    private String currency;
    private Boolean isFree;
}
