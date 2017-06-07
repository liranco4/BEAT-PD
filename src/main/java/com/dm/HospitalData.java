package com.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by liran on 5/4/17.
 */
@Entity(name = "HOSPITAL_DATA")
public class HospitalData {

    @Id
    @Column(name = "PATIENT_ID")
    private String patientID;

    @Column(name = "PATIENT_NAME")
    private String patientName;

    @Override
    public String toString(){
        return String.format("{patientID:%s,patientName:%s}",patientID, patientName);
    }

    public HospitalData(){}
    public HospitalData(String patientID, String patientName) {
        this.patientID = patientID;
        this.patientName = patientName;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
