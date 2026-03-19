package com.hydravision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydravision.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Leave this completely empty! Spring writes the save/find methods for us.
}