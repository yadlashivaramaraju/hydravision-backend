package com.hydravision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
 // 1. Endpoint to Approve a Proposal
    @PutMapping("/approve/{id}")
    public Proposal approveProposal(@PathVariable Long id) {
        Proposal proposal = proposalRepository.findById(id).orElseThrow(() -> new RuntimeException("Proposal not found"));
        proposal.setStatus("APPROVED");
        return proposalRepository.save(proposal);
    }

    // 2. Endpoint to Reject a Proposal
    @PutMapping("/reject/{id}")
    public Proposal rejectProposal(@PathVariable Long id) {
        Proposal proposal = proposalRepository.findById(id).orElseThrow(() -> new RuntimeException("Proposal not found"));
        proposal.setStatus("REJECTED");
        return proposalRepository.save(proposal);
    }
}