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

    @Column(name = "STATUS")
    private String patientStatus;

    @Column(name = "AGE")
    private String patientAge;

    @Column(name = "PASSWORD")
    private String patientPass;


    @Override
    public String toString(){
        return String.format("{patientID:\"%s\",patientFirstName:\"%s\",patientLastName:\"%s\",patientStatus:\"%s\",patientAge:\"%s\",patientPass:\"%s\"}", patientID, patientFirstName, patientLastName, patientStatus, patientAge,patientPass);
    }

    public Patient(){
    }

    public Patient(String patientID, String patientFirstName, String patientLastName, String patientStatus, String patientAge, String patientPass) {
        this.patientID = patientID;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientStatus = patientStatus;
        this.patientAge = patientAge;
        this.patientPass = patientPass;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
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

    public String getPatientPass() {
        return patientPass;
    }

    public void setPatientPass(String patientPass) {
        this.patientPass = patientPass;
    }
}
