package com.hydravision.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "proposals")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agencyEmail;
    
    @Column(length = 1000) // Needs to be long enough to hold the list of screen names
    private String selectedScreens; 
    
    private Double totalCost;
    private String status = "PENDING"; // PENDING, APPROVED, REJECTED
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAgencyEmail() { return agencyEmail; }
    public void setAgencyEmail(String agencyEmail) { this.agencyEmail = agencyEmail; }
    public String getSelectedScreens() { return selectedScreens; }
    public void setSelectedScreens(String selectedScreens) { this.selectedScreens = selectedScreens; }
    public Double getTotalCost() { return totalCost; }
    public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}