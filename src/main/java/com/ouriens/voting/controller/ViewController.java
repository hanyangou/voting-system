package com.ouriens.voting.controller;

import com.ouriens.voting.controller.domain.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @Autowired
    VotingController votingController;

    @GetMapping("/view/main")
    public String voteMain(Model model) {
        Poll poll = votingController.getPoll();
        model.addAttribute("id", poll.getId());
        model.addAttribute("topic", poll.getTopic());
        model.addAttribute("option1", poll.getOption1());
        model.addAttribute("option1Vote", poll.getOption1Count());
        model.addAttribute("option2", poll.getOption2());
        model.addAttribute("option2Vote", poll.getOption2Count());
        model.addAttribute("option3", poll.getOption3());
        model.addAttribute("option3Vote", poll.getOption3Count());
        model.addAttribute("pollStatus", poll.isOngoing());
        return "main";
    }
}
