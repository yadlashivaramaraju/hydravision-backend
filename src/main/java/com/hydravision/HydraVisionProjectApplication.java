package com.hydravision; // Keep your original package name here!

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hydravision.entity.Screen;
import com.hydravision.entity.User;
import com.hydravision.repository.ScreenRepository;
import com.hydravision.repository.UserRepository;

@SpringBootApplication
public class HydraVisionProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(HydraVisionProjectApplication.class, args);
    }

    // This runs automatically every time the server starts!
    @Bean
    public CommandLineRunner seedDatabase(UserRepository userRepository, ScreenRepository screenRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setName("HydraVision Admin");
                admin.setEmail("admin@hydravision.com");
                admin.setPassword("password123");
                admin.setRole("ADMIN");
                userRepository.save(admin);
                System.out.println("✅ Dummy User Created (ID: 1)");
            }

            if (screenRepository.count() == 0) {
                Screen screen = new Screen();
                screen.setLocationName("Times Square - Red Box");
                screen.setResolution("1920x1080");
                screen.setBasePrice(500.0);
                screen.setStatus("ACTIVE");
                screenRepository.save(screen);
                System.out.println("✅ Dummy Screen Created (ID: 1)");
            }
        };
    }
}