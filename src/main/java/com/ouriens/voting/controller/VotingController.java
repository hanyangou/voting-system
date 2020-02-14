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
        if(!poll.isOngoing()) return;
        if(option > poll.getTotalOptions()) return;

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

    @RequestMapping(value = "/vote/pause", method = {RequestMethod.GET})
    public Poll pausePoll(){
        poll.setOngoing(false);
        return poll;
    }

    @RequestMapping(value = "/vote/resume", method = {RequestMethod.GET})
    public Poll resumePoll(){
        poll.setOngoing(true);
        return poll;
    }

    @RequestMapping(value = "/vote/create/{topic}/{option1}/{option2}", method = {RequestMethod.GET})
    public Poll initTwoOptions(@PathVariable String topic, @PathVariable String option1,@PathVariable String option2){
        poll.setTopic(topic.trim());
        poll.setOption1(option1.trim());
        poll.setOption2(option2.trim());
        poll.setOngoing(true);
        poll.setOption1Count(0);
        poll.setOption2Count(0);
        poll.setTotalOptions(2);
        return poll;
    }

    @RequestMapping(value = "/vote/create/{topic}/{option1}/{option2}/{option3}", method = {RequestMethod.GET})
    public Poll initThreeOptions(@PathVariable String topic, @PathVariable String option1,@PathVariable String option2 ,@PathVariable String option3){
        poll.setTopic(topic.trim());
        poll.setOption1(option1.trim());
        poll.setOption2(option2.trim());
        poll.setOption3(option3.trim());
        poll.setOngoing(true);
        poll.setOption1Count(0);
        poll.setOption2Count(0);
        poll.setOption3Count(0);
        poll.setTotalOptions(3);
        return poll;
    }
}