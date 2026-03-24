package com.example.demo.Controller;


import com.example.demo.Model.Museum1;
import com.example.demo.Service.Museum1Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController                    // ✅ no argument
@RequestMapping("/testing")        // ✅ URL goes here
public class TestController {

        // Get all museums
        private final Museum1Service museum1Service;

    public TestController(Museum1Service museum1Service) {
        this.museum1Service = museum1Service;
    }

    // Get all museums

}
