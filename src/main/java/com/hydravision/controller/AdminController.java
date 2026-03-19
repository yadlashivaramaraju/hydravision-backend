package com.hydravision.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping; // Added the proper import here!
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydravision.entity.Booking;
import com.hydravision.repository.BookingRepository;

@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private BookingRepository bookingRepository;

    // Returns ALL bookings so Admin sees Pending, and Billboard sees Approved
    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Explicitly handles the Approve button
    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveBooking(@PathVariable Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setStatus("APPROVED");
            bookingRepository.save(booking);
            return ResponseEntity.ok("Booking Approved");
        }
        return ResponseEntity.badRequest().body("Booking not found");
    }

    // Explicitly handles the Reject button
    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectBooking(@PathVariable Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setStatus("REJECTED");
            bookingRepository.save(booking);
            return ResponseEntity.ok("Booking Rejected");
        }
        return ResponseEntity.badRequest().body("Booking not found");
    }
    
    // Explicitly handles the Delete button
    @DeleteMapping("/delete/{id}") // Cleaned this up!
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        
        if (optionalBooking.isPresent()) {
            bookingRepository.deleteById(id);
            return ResponseEntity.ok("Booking Deleted Successfully");
        }
        return ResponseEntity.badRequest().body("Booking not found");
    }
}