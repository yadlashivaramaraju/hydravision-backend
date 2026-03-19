package com.hydravision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydravision.entity.Booking;
import com.hydravision.repository.BookingRepository;

@RestController
@RequestMapping("/api/simulator")
public class SimulatorController {

    @Autowired
    private BookingRepository bookingRepository;

    // The React Billboard screen will call this API every 10 seconds
    @GetMapping("/screen/{screenId}/live")
    public List<Booking> getLiveAds(@PathVariable Long screenId) {
        // ONLY return ads that the Admin has explicitly APPROVED
        return bookingRepository.findByScreenIdAndStatus(screenId, "APPROVED");
    }
}