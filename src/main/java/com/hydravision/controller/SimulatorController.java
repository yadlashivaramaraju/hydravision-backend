package com.hydravision.controller;

import com.hydravision.entity.Booking;
import com.hydravision.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulator")
@CrossOrigin(origins = "*") // I added this so your React frontend can talk to it without CORS errors!
public class SimulatorController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/screen/{screenId}/live")
    public List<Booking> getLiveAds(@PathVariable Long screenId) {
        return bookingRepository.findByScreenIdAndStatus(screenId, "APPROVED");
    }
}