package com.hydravision.controller;

import com.hydravision.entity.Booking;
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
            String imagePath = fileStorageService.saveFile(imageFile);

            Booking booking = new Booking();
            booking.setUserId(userId);
            booking.setScreenId(screenId);
            booking.setTimeSlot(timeSlot);
            booking.setAmountPaid(amountPaid);
            booking.setImagePath(imagePath);
            booking.setStatus("PENDING");

            bookingRepository.save(booking);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to create booking");
        }
    }
}