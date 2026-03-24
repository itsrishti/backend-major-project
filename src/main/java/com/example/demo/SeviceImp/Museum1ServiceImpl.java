package com.example.demo.SeviceImp;



import com.example.demo.Model.Museum;
import com.example.demo.Model.Museum1;
import com.example.demo.Repository.Museum1Repository;
import com.example.demo.Repository.MuseumRepository;
import com.example.demo.Service.Museum1Service;
import com.example.demo.Service.MuseumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class Museum1ServiceImpl implements Museum1Service {

    private final Museum1Repository museumRepository;
    private final MongoTemplate mongoTemplate;

    // ✅ CREATE
    @Override
    public Museum1 createMuseum(Museum1 museum) {
        museum.setCreatedAt(new Date());
        museum.setUpdatedAt(new Date());
        return museumRepository.save(museum);
    }

    // ✅ GET BY ID
    @Override
    public Optional<Museum1> getMuseumById(String id) {
        return museumRepository.findById(id);
    }

    // ✅ GET ALL
    @Override
    public List<Museum1> getAllMuseums() {
        return museumRepository.findAll();
    }

    // ✅ UPDATE
    @Override
    public Museum1 updateMuseum(String id, Museum1 updatedMuseum) {

        return museumRepository.findById(id)
                .map(existing -> {

                    existing.setName(updatedMuseum.getName());
                    existing.setAddress(updatedMuseum.getAddress());
                    existing.setOverview(updatedMuseum.getOverview());

                    existing.setThemes(updatedMuseum.getThemes());
                    existing.setWebsite(updatedMuseum.getWebsite());

                    existing.setContactInfo(updatedMuseum.getContactInfo());
                    existing.setTicketPrices(updatedMuseum.getTicketPrices());
                    existing.setOperatingHours(updatedMuseum.getOperatingHours());

                    existing.setFacilities(updatedMuseum.getFacilities());

                    existing.setUpdatedAt(new Date()); // ✅ important

                    return museumRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Museum not found with id: " + id));
    }

    // ✅ DELETE
    @Override
    public void deleteMuseum(String id) {
        museumRepository.deleteById(id);
    }

    // ✅ SEARCH WITH PAGINATION
    @Override
    public Page<Museum1> searchMuseums(String keyword, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        // 🔥 Handle empty keyword (return all paginated)
        if (keyword == null || keyword.trim().isEmpty()) {
            Page<Museum1> all = museumRepository.findAll(pageable);
            return new PageImpl<>(all.getContent(), pageable, all.getTotalElements());
        }

        String[] tokens = keyword.trim().split("\\s+");

        List<Criteria> criteriaList = new ArrayList<>();

        for (String token : tokens) {

            Criteria criteria = new Criteria().orOperator(

                    // Basic fields
                    Criteria.where("name").regex(token, "i"),
                    Criteria.where("overview").regex(token, "i"),

                    // Nested fields
                    Criteria.where("address.street").regex(token, "i"),
                    Criteria.where("address.state").regex(token, "i"),

                    // Array fields (IMPORTANT FIX)
                    Criteria.where("themes").in(token)
            );

            criteriaList.add(criteria);
        }

        // AND all tokens
        Criteria finalCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));

        Query query = new Query(finalCriteria);
        query.with(pageable);

        List<Museum1> museums = mongoTemplate.find(query, Museum1.class);

        // ✅ Separate count query (VERY IMPORTANT)
        Query countQuery = new Query(finalCriteria);
        long count = mongoTemplate.count(countQuery, Museum1.class);

        return new PageImpl<>(museums, pageable, count);
    }
}