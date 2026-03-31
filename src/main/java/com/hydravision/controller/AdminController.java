package com.hydravision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydravision.entity.Booking;
import com.hydravision.repository.BookingRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "https://hydravision-frontend.vercel.app") // Allows Vercel to connect
public class AdminController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveBooking(@PathVariable Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        booking.setStatus("APPROVED");
        bookingRepository.save(booking);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectBooking(@PathVariable Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        booking.setStatus("REJECTED");
        bookingRepository.save(booking);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        bookingRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}