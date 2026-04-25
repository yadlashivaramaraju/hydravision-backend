package com.hydravision.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydravision.entity.Proposal;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}
