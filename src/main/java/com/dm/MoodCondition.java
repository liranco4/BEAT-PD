package com.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static java.lang.String.format;

/**
 * Created by liran on 06/06/17.
 */
@Entity(name="MOOD_CONDITION")
public class MoodCondition {
    @Id
    @Column(name = "MOOD_CONDITION_NAME")
    private String moodConditionName;

    public String getMoodConditionName() {
        return moodConditionName;
    }

    public void setMoodConditionName(String moodConditionName) {
        this.moodConditionName = moodConditionName;
    }

    public MoodCondition() {
    }

    @Override
    public String toString(){
        return format("{moodConditionName:\"%s\"}",moodConditionName);
    }
}


