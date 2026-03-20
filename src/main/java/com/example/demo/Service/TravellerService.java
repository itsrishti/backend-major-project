package com.example.demo.Service;

import com.example.demo.Model.Traveller;
import java.util.List;

public interface TravellerService {

    Traveller createTraveller(Traveller traveller);

    List<Traveller> getTravellersByUser(String userId);

    Traveller getTravellerById(String id);

    Traveller updateTraveller(String id, Traveller traveller);

    void deleteTraveller(String id);
}