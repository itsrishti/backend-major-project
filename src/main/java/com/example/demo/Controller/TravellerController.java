package com.example.demo.Controller;

import com.example.demo.Model.Traveller;
import com.example.demo.Service.TravellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travellers")
@RequiredArgsConstructor
public class TravellerController {

    private final TravellerService travellerService;

    // CREATE
    @PostMapping
    public Traveller createTraveller(@RequestBody Traveller traveller) {
        return travellerService.createTraveller(traveller);
    }

    // GET all travellers by userId
    @GetMapping("/user/{userId}")
    public List<Traveller> getTravellersByUser(@PathVariable String userId) {
        return travellerService.getTravellersByUser(userId);
    }

    // GET single traveller
    @GetMapping("/{id}")
    public Traveller getTravellerById(@PathVariable String id) {
        return travellerService.getTravellerById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Traveller updateTraveller(@PathVariable String id,
                                     @RequestBody Traveller traveller) {
        return travellerService.updateTraveller(id, traveller);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteTraveller(@PathVariable String id) {
        travellerService.deleteTraveller(id);
    }
}