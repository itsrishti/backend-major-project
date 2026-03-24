package com.example.demo.Service;



import com.example.demo.Model.Museum;
import com.example.demo.Model.Museum1;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface Museum1Service {

    Museum1 createMuseum(Museum1 museum);

    Optional<Museum1> getMuseumById(String id);

    List<Museum1> getAllMuseums();

    Museum1 updateMuseum(String id, Museum1 museum);

    void deleteMuseum(String id);

    Page<Museum1> searchMuseums(String keyword, int page, int size);
}