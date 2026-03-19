package com.hydravision.controller;

import com.hydravision.entity.Screen;
import com.hydravision.entity.User;
import com.hydravision.repository.ScreenRepository;
import com.hydravision.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class SetupController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @GetMapping("/api/setup")
    public String primeDatabase() {
        try {
            if (userRepository.count() == 0) {
                userRepository.save(new User());
            }
            if (screenRepository.count() == 0) {
                screenRepository.save(new Screen());
            }
            return "SUCCESS: Database Primed! You can now book your first ad.";
        } catch (Exception e) {
            return "ALMOST THERE: You have required fields in your User or Screen entity that need to be filled out. Let me know what this says: " + e.getMessage();
        }
    }
}