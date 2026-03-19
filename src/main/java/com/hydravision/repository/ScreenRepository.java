package com.hydravision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydravision.entity.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    // Leave this completely empty! 
}