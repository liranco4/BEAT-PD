package com.dm.updateDM;

import com.interfaces.UpdateDM;

import javax.persistence.*;

import static java.lang.String.format;

/**
 * Created by liran on 06/06/17.
 */
@Entity(name = "SLEEP_DISORDER_UPDATE")
public class SleepDisorderUpdate implements UpdateDM{
    @Id
    @SequenceGenerator(name = "SLEEP_DISORDER_UPDATE_SEQ", sequenceName = "SLEEP_DISORDER_UPDATE_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SLEEP_DISORDER_UPDATE_SEQ")
    @Column(name = "SLEEP_DISORDER_UPDATE_ID")
    private Long sleepDisorderUpdateID;

    @Column(name = "SLEEP_DISORDER_UPDATE_NAME")
    private String sleepDisorderName;

    public SleepDisorderUpdate() {}

    @Override
    public String getFirstDetail() {
        return sleepDisorderName;
    }

    public void setSleepDisorderName(String sleepDisorderName) {
        this.sleepDisorderName = sleepDisorderName;
    }

    @Override
    public String toString(){
        return format("{sleepDisorderName:\"%s\",sleepDisorderUpdateID:\"%d\"}",sleepDisorderName,sleepDisorderUpdateID);
    }

    public Long getSleepDisorderUpdateID() {
        return sleepDisorderUpdateID;
    }

    public void setSleepDisorderUpdateID(Long sleepDisorderUpdateID) {
        this.sleepDisorderUpdateID = sleepDisorderUpdateID;
    }
}
