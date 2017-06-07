package com.dm;

import org.hibernate.annotations.Columns;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static java.lang.String.format;

/**
 * Created by liran on 06/06/17.
 */
@Entity(name = "SLEEP_DISORDER")
public class SleepDisorder {
    @Id
    @Column(name = "SLEEP_DISORDER_NAME")
    private String sleepDisorderName;

    public SleepDisorder() {}

    public String getSleepDisorderName() {
        return sleepDisorderName;
    }

    public void setSleepDisorderName(String sleepDisorderName) {
        this.sleepDisorderName = sleepDisorderName;
    }

    @Override
    public String toString(){
        return format("{sleepDisorderName:\"%s\"}",sleepDisorderName);
    }
}
