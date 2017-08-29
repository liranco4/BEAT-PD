package com.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static java.lang.String.format;

/**
 * Created by liran on 18/08/17.
 */
@Entity(name="SLEEP_QUALITY")
public class SleepQuality {
    @Id
    @Column(name="SLEEP_QUALITY_NAME")
    private String sleepQualityName;
    public SleepQuality(){}

    public String getSleepQualityName() {
        return sleepQualityName;
    }

    public void setSleepQualityName(String sleepQualityName) {
        this.sleepQualityName = sleepQualityName;
    }

    @Override
    public String toString(){
        return format("{sleepQualityName:\"%s\"}", sleepQualityName);
    }
}
