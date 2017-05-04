package com.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by liran on 5/4/17.
 */
@Entity(name = "HOSPITAL_DATA")
public class HospitalData {

    public HospitalData(){}
    @Id
    @Column(name = "PATIENT_ID")
    private String id;

    @Column(name = "PATIENT_NAME")
    private String name;

    public HospitalData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
