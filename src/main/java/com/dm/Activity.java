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
    private String name;

    @Column(name = "TYPE")
    private String activityType;//TODO need to add enum

    @Lob
    @Column(name = "LEMITATION")
    private String activityLemitation;

    @Override
    public String toString(){
        return String.format("{name:%s,activityType:%s,activityLemitation:%s}", name, activityType, activityLemitation);
    }

    public Activity(String name, String activityType, String activityLemitation) {
        this.name = name;
        this.activityType = activityType;
        this.activityLemitation = activityLemitation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
