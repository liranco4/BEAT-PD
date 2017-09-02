package com.dm;

import com.dao.ModelGenerics;
import com.dm.updateDM.SleepDisorderUpdate;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static com.utils.Utils.getObjectListAsJsonList;
import static java.lang.String.format;

/**
 * Created by liran on 06/06/17.
 */
public class SleepConditionAndDisorder {

    private Long sleepConditionID;
    private Long sleepHours;
    private String sleepQuality;
    private List<SleepDisorderUpdate> sleepDisorders = new ArrayList<>();

    public SleepConditionAndDisorder(){
    }

    public SleepConditionAndDisorder(long sleepHours, String sleepQuality, Collection<SleepDisorderUpdate> sleepDisorders){
        this.sleepHours = sleepHours;
        this.sleepQuality = sleepQuality;
        this.sleepDisorders.addAll(sleepDisorders);
    }

    public Long getSleepConditionName() {
        return sleepConditionID;
    }

    @Override
    public String toString(){
        return format("{sleepConditionID:\"%d\",sleepHours:\"%d\",sleepQuality:\"%s\",sleepDisorders:%s}",sleepConditionID,sleepHours,sleepQuality,
                getObjectListAsJsonList(sleepDisorders));
    }

    public Long getSleepConditionID() {
        return sleepConditionID;
    }

    public void setSleepConditionID(Long sleepConditionID) {
        this.sleepConditionID = sleepConditionID;
    }

    public Long getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(Long sleepHours) {
        this.sleepHours = sleepHours;
    }

    public String getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(String sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public List<SleepDisorderUpdate> getSleepDisorders() {
        return sleepDisorders;
    }

    public void setSleepDisorders(List<SleepDisorderUpdate> sleepDisorders) {
        this.sleepDisorders = sleepDisorders;
    }
}
