package com.hydravision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydravision.entity.Screen;
import com.hydravision.repository.ScreenRepository;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "https://hydravision-frontend.vercel.app")
public class InventoryController {

    @Autowired
    private ScreenRepository screenRepository;

    // React will call this to populate the Mapbox/Leaflet markers
    @GetMapping("/map-nodes")
    public ResponseEntity<List<Screen>> getMapInventory() {
        try {
            // Only return screens that are currently ACTIVE
            // (You might need to add findByStatus to your ScreenRepository if you want to filter, 
            // but findAll() works for now)
            List<Screen> activeScreens = screenRepository.findAll(); 
            return ResponseEntity.ok(activeScreens);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}