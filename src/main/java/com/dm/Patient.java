package com.dm;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * Created by liran on 27/05/17.
 */
@Entity(name = "PATIENT_DETAILS")
public class Patient {
    @Id
    @Column(name = "PATIENT_ID")
    private String patientID;

    @Column(name = "FIRST_NAME")
    private String patientFirstName;

    @Column(name = "LAST_NAME")
    private String patientLastName;

    @Column(name = "STATUS") //TODO ENUM FOR ALL STATUS
    private String patientStatus;

    @Column(name = "AGE")
    private String patientAge;

    @Override
    public String toString(){
        return String.format("{patientID:\"%s\",patientFirstName:\"%s\",patientLastName:\"%s\",patientStatus:\"%s\",patientAge:\"%s\"}", patientID, patientFirstName, patientLastName, patientStatus, patientAge);
    }

    public Patient(){
    }

    public Patient(String patientID, String patientFirstName, String patientLastName, String patientStatus, String patientAge) {
        this.patientID = patientID;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientStatus = patientStatus;
        this.patientAge = patientAge;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientStatus() {
        return patientStatus;
    }

    public void setPatientStatus(String patientStatus) {
        this.patientStatus = patientStatus;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }
}
