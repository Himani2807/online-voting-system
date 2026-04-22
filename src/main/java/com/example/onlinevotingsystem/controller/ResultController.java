package com.example.onlinevotingsystem.controller;

import com.example.onlinevotingsystem.service.ResultService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/results")
    public String showResults(Model model) {
        model.addAttribute("results", resultService.getResults());
        return "results";
    }

    @GetMapping("/winner-page")
    public String showWinner(Model model) {
        model.addAttribute("winner", resultService.getWinner());
        return "winner";
    }

    @GetMapping("/results/winner")
    public String winnerRedirect() {
        return "redirect:/winner-page";
    }
}