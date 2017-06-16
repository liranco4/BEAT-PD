package com.dm;

import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.*;

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

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="PATIENT_RECORD_ACTIVITY", joinColumns = {@JoinColumn(name = "PATIENT_RECORDS_ID")},inverseJoinColumns = {@JoinColumn(name="ACTIVITY_NAME")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private Collection<Activity> listOfActivitiy = new ArrayList<>();


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="PATIENT_RECORD_MEDICINE", joinColumns = {@JoinColumn(name = "PATIENT_RECORDS_ID")}, inverseJoinColumns ={@JoinColumn(name="MEDICINE_NAME")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private Collection<Medicine> listOfMedicine = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="PATIENT_RECORD_HABITS", joinColumns = {@JoinColumn(name = "PATIENT_RECORDS_ID")}, inverseJoinColumns ={@JoinColumn(name="HABIT_NAME")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private Collection<Habit> listOfHabit = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="PATIENT_RECORD_MOOD_CONDITION", joinColumns = {@JoinColumn(name = "PATIENT_RECORDS_ID")}, inverseJoinColumns ={@JoinColumn(name="MOOD_CONDITION_NAME")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private Collection<MoodCondition> listOfMoodCondition = new ArrayList<>();

    @Column(name = "SLEEP_CONDITION_ID")
    private  Long sleepConditionID;

    @Transient
    private SleepCondition sleepCondition;

    @Override
    public String toString(){
        return String.format("{patientRecordID:%d,patientID:%s,patientLastUpdate:%s,listOfActivitiy:%s,listOfMedicine:%s,listOfHabit:%s,listOfMoodCondition:%s,sleepCondition:%s}",
                patientRecordID, patientID,patientLastUpdate,listOfActivitiy,listOfMedicine, listOfHabit, listOfMoodCondition, sleepCondition);
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

    public Collection<Habit> getListOfHabit() {
        return listOfHabit;
    }

    public void setListOfHabit(Collection<Habit> listOfHabit) {
        this.listOfHabit = listOfHabit;
    }

    public Collection<MoodCondition> getListOfMoodCondition() {
        return listOfMoodCondition;
    }

    public void setListOfMoodCondition(Collection<MoodCondition> listOfMoodCondition) {
        this.listOfMoodCondition = listOfMoodCondition;
    }

    public SleepCondition getSleepCondition() {
        return sleepCondition;
    }

    public void setSleepCondition(SleepCondition sleepCondition) {
        this.sleepCondition = sleepCondition;
    }

    public Long getSleepConditionID() {
        return sleepConditionID;
    }

    public void setSleepConditionID(Long sleepConditionID) {
        this.sleepConditionID = sleepConditionID;
    }
}
