package com.dm;

import com.dao.ModelGenerics;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import static java.lang.String.format;

/**
 * Created by liran on 06/06/17.
 */
@Entity(name="SLEEP_CONDITION")
public class SleepCondition {
    //private static long sequence = 0;
    @Id
    @SequenceGenerator(name = "SLEEP_CONDITION_SEQ", sequenceName = "SLEEP_CONDITION_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SLEEP_CONDITION_SEQ")
    @Column(name = "SLEEP_CONDITION_ID")
    private Long sleepConditionID;

    @Column(name = "SLEEP_HOURS")
    private Long sleepHours;

    @Column(name = "SLEEP_QUALITY")
    private String sleepQuality;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="SLEEP_CONDITION_DISORDERS", joinColumns = {@JoinColumn(name = "SLEEP_CONDITION_ID")},inverseJoinColumns = {@JoinColumn(name="SLEEP_DISORDER_NAME")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private Collection<SleepDisorder> sleepDisorders = new ArrayList<>();

    public SleepCondition(){
    }

    public SleepCondition(long sleepHours, String sleepQuality, Collection<SleepDisorder> sleepDisorders){
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
                ModelGenerics.getModelGenericsInstance().getObjectListAsJsonList(sleepDisorders));
    }

    public Long getSleepConditionID() {
        return sleepConditionID;
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

    public Collection<SleepDisorder> getSleepDisorders() {
        return sleepDisorders;
    }

    public void setSleepDisorders(Collection<SleepDisorder> sleepDisorders) {
        this.sleepDisorders = sleepDisorders;
    }
}
