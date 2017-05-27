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
@Entity(name = "PATIENT_RECORD")
public class PatientRecord {
    @Id
    @Column(name = "PATIENT_RECORDS_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long patientRecordID;

    @Column(name = "PATIENT_ID")
    private String patientID;

    @Column(name = "LAST_UPDATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "date")
    private Date patientLastUpdate;

    @ElementCollection
    @JoinTable(name="PATIENT_RECORD_ACTIVITY", joinColumns = {@JoinColumn(name = "PATIENT_RECORDS_ID")},inverseJoinColumns = {@JoinColumn(name="ACTIVITY_NAME")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private Collection<Activity> listOfActivitiy = new ArrayList();

    @ElementCollection
    @JoinTable(name="PATIENT_RECORD_MEDICINE", joinColumns = {@JoinColumn(name = "PATIENT_RECORDS_ID")}, inverseJoinColumns ={@JoinColumn(name="MEDICINE_NAME")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private Collection<Medicine> listOfMedicine = new ArrayList();

    @Override
    public String toString(){
        return String.format("{patientRecordID:%d,patientID:%s,patientLastUpdate:%s,listOfActivitiy:%s,listOfMedicine:%s}", patientRecordID, patientID,patientLastUpdate,listOfActivitiy,listOfMedicine);
    }

    public PatientRecord(){
        this.patientLastUpdate = new Date();
    }

    public Long getPatientRecordID() {
        return patientRecordID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatient(String patientID) {
        this.patientID = patientID;
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
