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
            
            if (screenRepository.count() <= 1) {
                // Seed Node 1: Cyber Towers
                Screen node1 = new Screen();
                node1.setLocationName("HITEC City Cyber Towers");
                node1.setResolution("1920x1080");
                node1.setBasePrice(7500.0);
                node1.setStatus("ACTIVE");
                node1.setLatitude(17.4504);
                node1.setLongitude(78.3808);
                screenRepository.save(node1);
                
                // Seed Node 2: Mindspace
                Screen node2 = new Screen();
                node2.setLocationName("Mindspace Junction");
                node2.setResolution("1920x1080");
                node2.setBasePrice(5000.0);
                node2.setStatus("ACTIVE");
                node2.setLatitude(17.4401);
                node2.setLongitude(78.3811);
                screenRepository.save(node2);

                System.out.println("✅ Spatial Nodes Created in Database");
            }
        };
    }
}