package com.hydravision.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydravision.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByStatus(String string);
    // Spring Boot automatically writes all the SQL for us just by extending JpaRepository!

	List<Booking> findByScreenIdAndStatus(Long screenId, String string);
}