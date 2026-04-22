package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}