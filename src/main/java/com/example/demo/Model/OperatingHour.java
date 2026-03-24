package com.example.demo.Model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OperatingHour {
    private List<String> daysOfWeek;
    private Date openTime;
    private Date closeTime;
    private Boolean isClosed;
    private String note;
}
