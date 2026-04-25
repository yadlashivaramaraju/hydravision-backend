package com.hydravision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydravision.entity.Proposal;
import com.hydravision.repository.ProposalRepository;

@RestController
@RequestMapping("/api/proposals")
@CrossOrigin(origins = "*")
public class ProposalController {

    @Autowired
    private ProposalRepository proposalRepository;

    // The endpoint the green button will hit
    @PostMapping("/submit")
    public Proposal submitProposal(@RequestBody Proposal proposal) {
        return proposalRepository.save(proposal);
    }

    // The endpoint your Admin panel will use to view them
    @GetMapping("/all")
    public List<Proposal> getAllProposals() {
        return proposalRepository.findAll();
    }
}