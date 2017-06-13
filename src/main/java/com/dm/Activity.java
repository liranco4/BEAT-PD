package com.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Created by liran on 5/4/17.
 */
@Entity(name = "ACTIVITIES")
public class Activity {
    @Id
    @Column(name = "ACTIVITY_NAME")
    private String activityName;

    @Override
    public String toString(){
        return String.format("{activityName:\"%s\"}", activityName);
    }

    public Activity(String activityName) {
        this.activityName = activityName;

    }

    public Activity() {
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

}
