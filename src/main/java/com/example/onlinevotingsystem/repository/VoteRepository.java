package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v.candidateId, COUNT(v.id) FROM Vote v GROUP BY v.candidateId")
    List<Object[]> countVotes();
}