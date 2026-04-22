package com.example.onlinevotingsystem.controller;

import com.example.onlinevotingsystem.service.VoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/vote")
    public String vote(@RequestParam String name,
                       @RequestParam String email,
                       @RequestParam int candidateId,
                       Model model) {

        String message = voteService.vote(name, email, candidateId);

        model.addAttribute("message", message);
        model.addAttribute("candidateId", candidateId);

        return "success";
    }
}