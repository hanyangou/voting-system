package com.ouriens.voting.controller;

import com.ouriens.voting.controller.domain.Poll;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotingController {
    Poll poll = new Poll();

    @RequestMapping(value = "/admin/vote/update/{option}/{voteNumber}", method = RequestMethod.GET)
    public Poll updateVote(@PathVariable Integer option, @PathVariable Integer voteNumber){
        if(option > poll.getTotalOptions()) return poll;

        if(option == 1){
            poll.setOption1Count(voteNumber);
        } else if(option == 2){
            poll.setOption2Count(voteNumber);
        } else if(option == 3) {
            poll.setOption3Count(voteNumber);
        }

        return poll;
    }

    @RequestMapping(value = "/admin/vote/massive/{totalVote}/{option1Percentage}/{option2Percentage}", method = RequestMethod.GET)
    public Poll massiveVoteTwoOptions(@PathVariable Integer totalVote, @PathVariable Integer option1Percentage, @PathVariable Integer option2Percentage){
        if(poll.getTotalOptions() != 2) return poll;

        int total = option1Percentage + option2Percentage;
        int v1 = totalVote * option1Percentage / total;
        int v2 = totalVote * option2Percentage / total;

        int o1Total = poll.getOption1Count() + v1;
        poll.setOption1Count(o1Total);
        int o2Total = poll.getOption2Count() + v2;
        poll.setOption2Count(o2Total);

        return poll;
    }

    @RequestMapping(value = "/admin/vote/massive/{totalVote}/{option1Percentage}/{option2Percentage}/{option3Percentage}", method = RequestMethod.GET)
    public Poll massiveVoteThreeOptions(@PathVariable Integer totalVote, @PathVariable Integer option1Percentage, @PathVariable Integer option2Percentage, @PathVariable Integer option3Percentage){
        if(poll.getTotalOptions() != 3) return poll;

        int total = option1Percentage + option2Percentage + option3Percentage;
        int v1 = totalVote * option1Percentage / total;
        int v2 = totalVote * option2Percentage / total;
        int v3 = totalVote * option3Percentage / total;

        int o1Total = poll.getOption1Count() + v1;
        poll.setOption1Count(o1Total);
        int o2Total = poll.getOption2Count() + v2;
        poll.setOption2Count(o2Total);
        int o3Total = poll.getOption3Count() + v3;
        poll.setOption3Count(o3Total);

        return poll;
    }

    @RequestMapping(value = "/vote/incremental/{option1Vote}/{option2Vote}", method = RequestMethod.GET)
    public Poll incrementalVoteTwoOptions(@PathVariable Integer option1Vote, @PathVariable Integer option2Vote){
        if(!poll.isOngoing()) return poll;
        if(poll.getTotalOptions() != 2) return poll;

        int o1Total = poll.getOption1Count() + option1Vote;
        poll.setOption1Count(o1Total);
        int o2Total = poll.getOption2Count() + option2Vote;
        poll.setOption2Count(o2Total);

        return poll;
    }

    @RequestMapping(value = "/vote/incremental/{option1Vote}/{option2Vote}/{option3Vote}", method = RequestMethod.GET)
    public Poll incrementalVoteThreeOptions(@PathVariable Integer option1Vote, @PathVariable Integer option2Vote, @PathVariable Integer option3Vote){
        if(!poll.isOngoing()) return poll;
        if(poll.getTotalOptions() != 3) return poll;

        int o1Total = poll.getOption1Count() + option1Vote;
        poll.setOption1Count(o1Total);
        int o2Total = poll.getOption2Count() + option2Vote;
        poll.setOption2Count(o2Total);
        int o3Total = poll.getOption3Count() + option3Vote;
        poll.setOption3Count(o3Total);

        return poll;
    }

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

    @RequestMapping(value = "/vote/start", method = {RequestMethod.GET})
    public Poll startPoll(){
        return resumePoll();
    }

    @RequestMapping(value = "/vote/create/{id}/{topic}/{option1}/{option2}", method = {RequestMethod.GET})
    public Poll initTwoOptions(@PathVariable Integer id, @PathVariable String topic, @PathVariable String option1,@PathVariable String option2){
        poll = new Poll();
        poll.setId(id);
        poll.setTopic(topic.trim());
        poll.setOption1(option1.trim());
        poll.setOption2(option2.trim());
        poll.setOngoing(false);
        poll.setOption1Count(0);
        poll.setOption2Count(0);
        poll.setTotalOptions(2);
        return poll;
    }

    @RequestMapping(value = "/vote/create/{id}/{topic}/{option1}/{option2}/{option3}", method = {RequestMethod.GET})
    public Poll initThreeOptions(@PathVariable Integer id, @PathVariable String topic, @PathVariable String option1,@PathVariable String option2 ,@PathVariable String option3){
        poll = new Poll();
        poll.setId(id);
        poll.setTopic(topic.trim());
        poll.setOption1(option1.trim());
        poll.setOption2(option2.trim());
        poll.setOption3(option3.trim());
        poll.setOngoing(false);
        poll.setOption1Count(0);
        poll.setOption2Count(0);
        poll.setOption3Count(0);
        poll.setTotalOptions(3);
        return poll;
    }
}