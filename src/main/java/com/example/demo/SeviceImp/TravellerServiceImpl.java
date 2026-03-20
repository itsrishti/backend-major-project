package com.example.demo.SeviceImp;

import com.example.demo.Model.Traveller;
import com.example.demo.Repository.TravellerRepository;
import com.example.demo.Service.TravellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravellerServiceImpl implements TravellerService {

    private final TravellerRepository travellerRepository;

    @Override
    public Traveller createTraveller(Traveller traveller) {
        return travellerRepository.save(traveller);
    }

    @Override
    public List<Traveller> getTravellersByUser(String userId) {
        return travellerRepository.findByUserId(userId);
    }

    @Override
    public Traveller getTravellerById(String id) {
        return travellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Traveller not found"));
    }

    @Override
    public Traveller updateTraveller(String id, Traveller traveller) {
        Traveller existing = getTravellerById(id);

        existing.setName(traveller.getName());
        existing.setAge(traveller.getAge());
        existing.setGender(traveller.getGender());

        return travellerRepository.save(existing);
    }

    @Override
    public void deleteTraveller(String id) {
        travellerRepository.deleteById(id);
    }
}