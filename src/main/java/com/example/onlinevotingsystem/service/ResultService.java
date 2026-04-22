package com.example.onlinevotingsystem.service;

import com.example.onlinevotingsystem.model.Candidate;
import com.example.onlinevotingsystem.repository.CandidateRepository;
import com.example.onlinevotingsystem.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;

    public ResultService(VoteRepository voteRepository, CandidateRepository candidateRepository) {
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
    }

    public List<String> getResults() {
        List<Object[]> results = voteRepository.countVotes();
        List<String> finalResult = new ArrayList<>();

        for (Object[] row : results) {
            Integer candidateId = (Integer) row[0];
            Long votes = (Long) row[1];

            Candidate candidate = candidateRepository.findById(candidateId).orElse(null);

            if (candidate != null) {
                finalResult.add(candidate.getName() + " (" + candidate.getParty() + ") - " + votes + " votes");
            }
        }

        return finalResult;
    }

    public String getWinner() {
        List<Object[]> results = voteRepository.countVotes();

        if (results.isEmpty()) {
            return "No votes yet!";
        }

        long maxVotes = 0;
        String winnerName = "";
        String winnerParty = "";

        for (Object[] row : results) {
            Integer candidateId = (Integer) row[0];
            Long votes = (Long) row[1];

            Candidate candidate = candidateRepository.findById(candidateId).orElse(null);

            if (candidate != null && votes > maxVotes) {
                maxVotes = votes;
                winnerName = candidate.getName();
                winnerParty = candidate.getParty();
            }
        }

        if (winnerName.isEmpty()) {
            return "No votes yet!";
        }

        return "🏆 Winner is: " + winnerName + " (" + winnerParty + ") with " + maxVotes + " votes";
    }
}