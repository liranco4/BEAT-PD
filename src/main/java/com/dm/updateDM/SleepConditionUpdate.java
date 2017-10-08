package com.dm.updateDM;

import com.interfaces.UpdateDMProxy;

import javax.persistence.*;

import static com.utils.Utils.getObjectListAsJsonList;
import static java.lang.String.format;

/**
 * Created by liran on 02/09/17.
 */
@Entity(name="SLEEP_CONDITION")
public class SleepConditionUpdate implements UpdateDMProxy{

    @Id
    @SequenceGenerator(name = "SLEEP_CONDITION_SEQ", sequenceName = "SLEEP_CONDITION_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SLEEP_CONDITION_SEQ")
    @Column(name = "SLEEP_CONDITION_ID")
    private Long sleepConditionID;

    @Column(name = "SLEEP_HOURS")
    private Long sleepHours;

    @Column(name = "SLEEP_QUALITY")
    private String sleepQuality;

    public SleepConditionUpdate(){}

    public SleepConditionUpdate(Long i_SleepHours, String i_SleepQuality){
        sleepHours = i_SleepHours;
        sleepQuality = i_SleepQuality;
    }
    @Override
    public String toString(){
        return format("{sleepConditionID:\"%d\",sleepHours:\"%d\",sleepQuality:\"%s\"}",sleepConditionID,sleepHours,sleepQuality);
    }

    public Long getSleepConditionID() {
        return sleepConditionID;
    }

    public void setSleepConditionID(Long sleepConditionID) {
        this.sleepConditionID = sleepConditionID;
    }

    public Long getFirstDetail() {
        return sleepHours;
    }

    public String getSecondDetail() {
        return sleepQuality;
    }

    public void setSleepHours(Long sleepHours) {
        this.sleepHours = sleepHours;
    }

    public void setSleepQuality(String sleepQuality) {
        this.sleepQuality = sleepQuality;
    }
}
