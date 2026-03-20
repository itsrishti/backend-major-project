package com.example.demo.Repository;

import com.example.demo.Model.Traveller;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TravellerRepository extends MongoRepository<Traveller, String> {

    List<Traveller> findByUserId(String userId);
}