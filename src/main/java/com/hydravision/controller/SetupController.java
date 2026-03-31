package com.hydravision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydravision.entity.Screen;
import com.hydravision.entity.User;
import com.hydravision.repository.ScreenRepository;
import com.hydravision.repository.UserRepository;

@RestController
@CrossOrigin(origins = "https://hydravision-frontend.vercel.app")
public class SetupController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @GetMapping("/api/setup")
    public String primeDatabase() {
        StringBuilder status = new StringBuilder();

        // 1. Create the User
        try {
            if (userRepository.count() == 0) {
                User user = new User();
                user.setName("Founder");
                user.setEmail("founder@hydravision.com");
                user.setPassword("password123");
                user.setRole("ADMIN");
                userRepository.save(user);
                status.append("User created successfully! ");
            } else {
                status.append("User already exists. ");
            }
        } catch (Exception e) {
            return "USER ERROR: " + e.getMessage();
        }

        // 2. Create the Screen with the exact required fields
        try {
            if (screenRepository.count() == 0) {
                Screen screen = new Screen();
                screen.setBasePrice(4000.0);
                screen.setLocationName("Jubilee Hills Checkpost");
                screen.setResolution("1920x1080");
                screen.setStatus("ACTIVE");
                screenRepository.save(screen);
                status.append("Screen created successfully! ");
            } else {
                status.append("Screen already exists. ");
            }
        } catch (Exception e) {
            return status.toString() + " | SCREEN ERROR: " + e.getMessage();
        }

        return "SUCCESS: " + status.toString() + "Go back to your Vercel site and test the booking!";
    }
}