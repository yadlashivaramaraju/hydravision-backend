package com.hydravision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin; // <-- The VIP Pass for React!
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hydravision.entity.Booking;
import com.hydravision.entity.Screen;
import com.hydravision.entity.User;
import com.hydravision.repository.BookingRepository;
import com.hydravision.repository.ScreenRepository;
import com.hydravision.repository.UserRepository;
import com.hydravision.service.FileStorageService;

@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired private BookingRepository bookingRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ScreenRepository screenRepository;
    @Autowired private FileStorageService fileStorageService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(
            @RequestParam("userId") Long userId,
            @RequestParam("screenId") Long screenId,
            @RequestParam("timeSlot") String timeSlot,
            @RequestParam("amountPaid") Double amountPaid,
            @RequestParam("imageFile") MultipartFile imageFile) {

        try {
            String savedFileName = fileStorageService.saveImageLocally(imageFile);

            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            Screen screen = screenRepository.findById(screenId).orElseThrow(() -> new RuntimeException("Screen not found"));

            Booking booking = new Booking();
            booking.setUser(user);
            booking.setScreen(screen);
            booking.setTimeSlot(timeSlot);
            booking.setAmountPaid(amountPaid);
            booking.setImagePath(savedFileName);
            booking.setStatus("PENDING"); 

            bookingRepository.save(booking);

            return ResponseEntity.ok("Booking successful! Waiting for Admin approval.");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}