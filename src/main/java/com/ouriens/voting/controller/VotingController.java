package com.ouriens.voting.controller;

import com.ouriens.voting.controller.domain.Poll;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotingController {
    Poll poll = new Poll();

    @RequestMapping(value = "/vote/{option}", method = {RequestMethod.GET})
    public void vote(@PathVariable Integer option){
        Integer curCount;
        if(option == 1){
            curCount = poll.getOption1Count();
            curCount++;
            poll.setOption1Count(curCount);
        } else if (option == 2){
            curCount = poll.getOption2Count();
            curCount++;
            poll.setOption2Count(curCount);
        } else if (option == 3){
            curCount = poll.getOption3Count();
            curCount++;
            poll.setOption3Count(curCount);
        }
        else {
            throw new RuntimeException("Unsupported option");
        }
    }
    @RequestMapping(value = "/vote/result", method = {RequestMethod.GET})
    public Poll getResult(){
        return poll;
    }

    @RequestMapping(value = "/vote/create/{topic}/{option1}/{option2}/{option3}", method = {RequestMethod.GET})
    public Poll init(@PathVariable String topic, @PathVariable String option1,@PathVariable String option2 ,@PathVariable String option3){
        poll.setTopic(topic);
        poll.setOption1(option1);
        poll.setOption2(option2);
        poll.setOption3(option3);
        poll.setOngoing(true);
        poll.setOption1Count(0);
        poll.setOption2Count(0);
        poll.setOption3Count(0);
        return poll;
    }
}
