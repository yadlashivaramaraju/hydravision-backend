package com.hydravision.controller;

import com.hydravision.entity.Booking;
import com.hydravision.entity.Screen;
import com.hydravision.entity.User;
import com.hydravision.repository.BookingRepository;
import com.hydravision.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*") // Allows Vercel to connect
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(
            @RequestParam("userId") Long userId,
            @RequestParam("screenId") Long screenId,
            @RequestParam("timeSlot") String timeSlot,
            @RequestParam("amountPaid") Double amountPaid,
            @RequestParam("imageFile") MultipartFile imageFile) {

        try {
            // 1. FIXED: Calling your exact custom method name
            String imagePath = fileStorageService.saveImageLocally(imageFile);

            Booking booking = new Booking();

            // 2. FIXED: Setting the actual User object to satisfy the @ManyToOne relationship
            User user = new User();
            user.setId(userId);
            booking.setUser(user);

            // 3. FIXED: Setting the actual Screen object to satisfy the @ManyToOne relationship
            Screen screen = new Screen();
            screen.setId(screenId);
            booking.setScreen(screen);

            booking.setTimeSlot(timeSlot);
            booking.setAmountPaid(amountPaid);
            booking.setImagePath(imagePath);
            booking.setStatus("PENDING");

            bookingRepository.save(booking);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Failed to create booking");
        }
    }
}