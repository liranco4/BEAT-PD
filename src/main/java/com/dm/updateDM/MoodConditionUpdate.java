package com.dm.updateDM;

import com.interfaces.UpdateDM;

import javax.persistence.*;

import static java.lang.String.format;

/**
 * Created by liran on 10/6/17.
 */
@Entity(name="MOOD_CONDITION_UPDATE")
public class MoodConditionUpdate implements UpdateDM {

    @Id
    @SequenceGenerator(name = "MOOD_CONDITION_UPDATE_SEQ", sequenceName = "MOOD_CONDITION_UPDATE_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "MOOD_CONDITION_UPDATE_SEQ")
    @Column(name = "MOOD_CONDITION_ID")
    private Long moodConditionUpdateID;


    @Column(name = "MOOD_CONDITION_NAME")
    private String moodConditionName;

    @Override
    public String getFirstDetail() {
        return moodConditionName;
    }

    public void setMoodConditionName(String moodConditionName) {
        this.moodConditionName = moodConditionName;
    }

    public MoodConditionUpdate() {
    }

    @Override
    public String toString(){
        return format("{moodConditionName:\"%s\"}",moodConditionName);
    }
}

