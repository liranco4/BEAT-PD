package com.dm;

import com.interfaces.UpdateDM;

import javax.persistence.*;

import static java.lang.String.format;

/**
 * Created by liran on 12/08/17.
 */
@Entity(name="ACTIVITY_UPDATE")
public class ActivityUpdate implements UpdateDM{
    @Id
    @SequenceGenerator(name = "ACTIVITY_UPDATE_SEQ", sequenceName = "ACTIVITY_UPDATE_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ACTIVITY_UPDATE_SEQ")
    @Column(name = "ACTIVITY_UPDATE_ID")
    private Long activityUpdateID;

    @Column(name = "ACTIVITY_NAME")
    private String activityName;

    @Column(name="ACTIVITY_DESCRIPTION")
    private String activityDescription;

    public ActivityUpdate(){};

    @Override
    public String toString(){
        return format("{activityName:\"%s\",activityDescription:\"%s\"}",activityName,activityDescription);
    }

    public Long getActivityUpdateID() {
        return activityUpdateID;
    }

    public void setActivityUpdateID(Long activityUpdateID) {
        this.activityUpdateID = activityUpdateID;
    }

    @Override
    public String getName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Override
    public String getDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }
}
