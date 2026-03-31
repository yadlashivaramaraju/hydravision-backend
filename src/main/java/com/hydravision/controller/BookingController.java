package com.hydravision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hydravision.entity.Booking;
import com.hydravision.entity.Screen;
import com.hydravision.entity.User;
import com.hydravision.repository.BookingRepository;
import com.hydravision.service.FileStorageService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*") 
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FileStorageService fileStorageService;

    // ==========================================
    // 1. CREATE BOOKING (From Tourist)
    // ==========================================
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(
            @RequestParam("userId") Long userId,
            @RequestParam("screenId") Long screenId,
            @RequestParam("timeSlot") String timeSlot,
            @RequestParam("amountPaid") Double amountPaid,
            @RequestParam("imageFile") MultipartFile imageFile) {

        try {
            String imagePath = fileStorageService.uploadImageToCloudinary(imageFile);

            Booking booking = new Booking();
            
            User user = new User();
            user.setId(userId);
            booking.setUser(user);

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

    // ==========================================
    // 2. GET ALL BOOKINGS (For Admin Panel)
    // ==========================================
    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        try {
            List<Booking> allBookings = bookingRepository.findAll();
            return ResponseEntity.ok(allBookings);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ==========================================
    // 3. APPROVE AN AD
    // ==========================================
    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveBooking(@PathVariable Long id) {
        try {
            Booking booking = bookingRepository.findById(id).orElseThrow();
            booking.setStatus("APPROVED");
            bookingRepository.save(booking);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ==========================================
    // 4. REJECT AN AD
    // ==========================================
    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectBooking(@PathVariable Long id) {
        try {
            Booking booking = bookingRepository.findById(id).orElseThrow();
            booking.setStatus("REJECTED");
            bookingRepository.save(booking);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ==========================================
    // 5. DELETE AN AD
    // ==========================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        try {
            bookingRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}