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
    private String type;//TODO need to add enum

    @Lob
    @Column(name = "LEMITATION")
    private String lemitation;

    public Activity(){}

    public Activity(String name, String type, String lemitation) {
        this.name = name;
        this.type = type;
        this.lemitation = lemitation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLemitation() {
        return lemitation;
    }

    public void setLemitation(String lemitation) {
        this.lemitation = lemitation;
    }
}
