package com.ouriens.voting.controller.domain;

public class Poll {
    Integer id;
    String topic;
    String option1;
    String option2;
    String option3;
    Integer option1Count;
    Integer option2Count;
    Integer option3Count;
    Integer totalOptions;
    Integer duration;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalOptions() {
        return totalOptions;
    }

    public void setTotalOptions(Integer totalOptions) {
        this.totalOptions = totalOptions;
    }

    boolean ongoing;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public Integer getOption1Count() {
        return option1Count;
    }

    public void setOption1Count(Integer option1Count) {
        this.option1Count = option1Count;
    }

    public Integer getOption2Count() {
        return option2Count;
    }

    public void setOption2Count(Integer option2Count) {
        this.option2Count = option2Count;
    }

    public Integer getOption3Count() {
        return option3Count;
    }

    public void setOption3Count(Integer option3Count) {
        this.option3Count = option3Count;
    }

    public boolean isOngoing() {
        return ongoing;
    }

    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }
}
