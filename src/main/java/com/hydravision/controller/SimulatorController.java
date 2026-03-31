package com.hydravision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydravision.entity.Booking;
import com.hydravision.repository.BookingRepository;

@RestController
@RequestMapping("/api/simulator")
@CrossOrigin(origins = "https://hydravision-frontend.vercel.app") // I added this so your React frontend can talk to it without CORS errors!
public class SimulatorController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/screen/{screenId}/live")
    public List<Booking> getLiveAds(@PathVariable Long screenId) {
        return bookingRepository.findByScreenIdAndStatus(screenId, "APPROVED");
    }
}