package com.example.demo.Model;

import lombok.Data;

import java.util.List;

@Data
public class ContactInfo {
    private List<String> phoneNumbers;
    private List<String> emails;
}
