package com.example.onlinevotingsystem.service;

import com.example.onlinevotingsystem.model.Candidate;
import com.example.onlinevotingsystem.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
}