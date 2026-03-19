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
        StringBuilder status = new StringBuilder();

        // 1. Create the required User
        try {
            if (userRepository.count() == 0) {
                User user = new User();
                user.setName("Founder");
                user.setEmail("founder@hydravision.com");
                user.setPassword("password123");
                user.setRole("ADMIN");
                userRepository.save(user);
                status.append("User created safely! ");
            }
        } catch (Exception e) {
            return "USER ERROR: " + e.getMessage();
        }

        // 2. Create the required Screen
        try {
            if (screenRepository.count() == 0) {
                Screen screen = new Screen();
                screenRepository.save(screen);
                status.append("Screen created safely! ");
            }
        } catch (Exception e) {
            return status.toString() + " | SCREEN ERROR: " + e.getMessage();
        }

        return "SUCCESS: " + status.toString() + "Go to your Vercel site and book the ad!";
    }
}