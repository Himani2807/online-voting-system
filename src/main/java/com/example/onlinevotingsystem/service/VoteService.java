package com.example.onlinevotingsystem.service;

import com.example.onlinevotingsystem.model.User;
import com.example.onlinevotingsystem.model.Vote;
import com.example.onlinevotingsystem.repository.UserRepository;
import com.example.onlinevotingsystem.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    public VoteService(UserRepository userRepository, VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    public String vote(String name, String email, int candidateId) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword("1234");
            user.setRole("USER");
            user.setHasVoted(false);
            userRepository.save(user);
        }

        if (user.isHasVoted()) {
            return "You already voted!";
        }

        Vote vote = new Vote();
        vote.setUserId(user.getId());
        vote.setCandidateId(candidateId);
        voteRepository.save(vote);

        user.setHasVoted(true);
        userRepository.save(user);

        return "Vote successful!";
    }
}