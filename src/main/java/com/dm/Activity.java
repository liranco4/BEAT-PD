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

    @Column(name = "TYPE")
    private String activityType;//TODO need to add enum

    @Lob
    @Column(name = "LEMITATION")
    private String activityLemitation;

    @Override
    public String toString(){
        return String.format("{activityName:\"%s\",activityType:\"%s\",activityLemitation:\"%s\"}", activityName, activityType, activityLemitation);
    }

    public Activity(String activityName, String activityType, String activityLemitation) {
        this.activityName = activityName;
        this.activityType = activityType;
        this.activityLemitation = activityLemitation;
    }

    public Activity() {
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityLemitation() {
        return activityLemitation;
    }

    public void setActivityLemitation(String activityLemitation) {
        this.activityLemitation = activityLemitation;
    }
}
