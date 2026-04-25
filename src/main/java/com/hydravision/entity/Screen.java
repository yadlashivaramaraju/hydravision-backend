package com.hydravision.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "screens")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String locationName; // e.g., "Uppal X Roads"

    @Column(nullable = false)
    private Double basePrice; // e.g., 499.00

    private String resolution; // e.g., "1920x1080"
    
    private String status; // "ACTIVE" or "MAINTENANCE"
 
    // NEW FIELDS FOR THE SPATIAL MAP ENGINE
    private Double latitude;
    private Double longitude;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
    public Double getBasePrice() { return basePrice; }
    public void setBasePrice(Double basePrice) { this.basePrice = basePrice; }
    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
 // NEW GETTERS AND SETTERS
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
}