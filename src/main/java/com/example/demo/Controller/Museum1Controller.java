//package com.example.demo.Controller;
//
//import com.example.demo.Model.Museum1;
//import com.example.demo.Service.Museum1Service;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/museums1")
//
//
//public class Museum1Controller {
//
//    @GetMapping("test")
//    public String test(){
//        return "hello";
//    }
//
//    @Autowired
//    private final Museum1Service museum1Service;
//
//    public Museum1Controller(Museum1Service museum1Service) {
//        this.museum1Service = museum1Service;
//    }
//
//    // Create new museum
//    @PostMapping
//    public Museum1 createMuseum(@RequestBody Museum1 museum) {
//        return museum1Service.createMuseum(museum);
//    }
//
//    // Get museum by ID
//    @GetMapping("/{id}")
//    public Museum1 getMuseumById(@PathVariable String id) {
//        return museum1Service.getMuseumById(id)
//                .orElseThrow(() -> new RuntimeException("Museum not found with id: " + id));
//    }
//
//
//    // Update museum
//    @PutMapping("/{id}")
//    public Museum1 updateMuseum(@PathVariable String id, @RequestBody Museum1 museum) {
//        return museum1Service.updateMuseum(id, museum);
//    }
//
//    // Delete museum
//    @DeleteMapping("/{id}")
//    public void deleteMuseum(@PathVariable String id) {
//        museum1Service.deleteMuseum(id);
//    }
//
//    @GetMapping()
//    public List<Museum1> getAllMuseums() {
//        return museum1Service.getAllMuseums();
//    }
//}