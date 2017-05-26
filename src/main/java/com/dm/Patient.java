package com.dm;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.TemporalType.DATE;


/**
 * Created by liran on 5/4/17.
 */
@Entity(name = "PATIENT_DETAILS")
@Embeddable
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

    @Column(name = "LAST_UPDATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "date")
    private Date patientLastUpdate;

    @Override
    public String toString(){
        return String.format("{patientID:%s,patientFirstName:%s,patientLastName:%s,patientStatus:%s,patientAge:%s,listOfActivitiy:%s,listOfMedicine:%s, patientLastUpdate:%s}", patientID, patientFirstName, patientLastName, patientStatus, patientAge,listOfActivitiy,listOfMedicine,patientLastUpdate);
    }

    @ElementCollection
    @JoinTable(name="PATIENT_ACTIVITIY", joinColumns = {@JoinColumn(name = "PATIENT_NAME")},inverseJoinColumns = {@JoinColumn(name="ACTIVITY_NAME")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private Collection<Activity> listOfActivitiy = new ArrayList();

    @ElementCollection
    @JoinTable(name="PATIENT_MEDICINE", joinColumns = {@JoinColumn(name = "PATIENT_NAME")}, inverseJoinColumns ={@JoinColumn(name="MEDICINE_NAME")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private Collection<Medicine> listOfMedicine = new ArrayList();

    public Patient(){
        this.patientLastUpdate = new Date();
    }

    public Patient(String patientID, String patientFirstName, String patientLastName, String patientStatus, String patientAge) {
        this.patientID = patientID;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientStatus = patientStatus;
        this.patientAge = patientAge;
        this.patientLastUpdate = new Date();
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

    public Date getPatientLastUpdate() {
        return patientLastUpdate;
    }

    public void setPatientLastUpdate(Date patientLastUpdate) {
        this.patientLastUpdate = patientLastUpdate;
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
