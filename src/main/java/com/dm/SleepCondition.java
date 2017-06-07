package com.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static java.lang.String.format;

/**
 * Created by liran on 06/06/17.
 */
@Entity(name="SLEEP_CONDITION")
public class SleepCondition {
    @Id
    @Column(name = "SLEEP_CONDITION_NAME")
    private String sleepConditionName;  //TODO add time and quality

    public SleepCondition(){}

    public String getSleepConditionName() {
        return sleepConditionName;
    }

    public void setSleepConditionName(String sleepConditionName) {
        this.sleepConditionName = sleepConditionName;
    }

    @Override
    public String toString(){
        return format("{sleepConditionName:\"%s\"}",sleepConditionName);
    }
}
