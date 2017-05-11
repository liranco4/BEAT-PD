package com.dm;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by liran on 5/4/17.
 */
@Entity(name = "PATIENT_DETAILS")
public class Patient {
    @Id
    @Column(name = "PATIENT_NAME")
    private String patientName;

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
        return String.format("{patientName:%s,patientFirstName:%s,patientLastName:%s,patientStatus:%s,patientAge}", patientName, patientFirstName, patientLastName, patientStatus, patientAge);
    }

    @ElementCollection
    @JoinTable(name="PATIENT_AVTIVITIY", joinColumns = @JoinColumn(name = "PATIENT_NAME"))
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    @Column(name = "ACTIVITY_NAME")
    private Collection<Activity> listOfActivitiy = new ArrayList();

    @ElementCollection
    @JoinTable(name="PATIENT_MEDICINE", joinColumns = @JoinColumn(name = "PATIENT_NAME"))
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    @Column(name = "MEDICINE_NAME")
    private Collection<Medicine> listOfMedicine = new ArrayList();

    public Patient(String patientName, String patientFirstName, String patientLastName, String patientStatus, String patientAge) {
        this.patientName = patientName;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientStatus = patientStatus;
        this.patientAge = patientAge;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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

    public Collection<Activity> getListOfActivitiy() {
        return listOfActivitiy;
    }

    public void setListOfActivitiy(Collection<Activity> listOfActivitiy) {
        this.listOfActivitiy = listOfActivitiy;
    }

    public Collection<Medicine> getListOfMedicine() {
        return listOfMedicine;
    }

    public void setListOfMedicine(Collection<Medicine> listOfMedicine) {
        this.listOfMedicine = listOfMedicine;
    }
}
