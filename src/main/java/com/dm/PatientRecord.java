package com.dm;

import com.dm.updateDM.*;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.*;


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

    @ManyToMany(fetch = FetchType.EAGER,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="PATIENT_RECORD_ACTIVITY", joinColumns = {@JoinColumn(name = "PATIENT_RECORDS_ID")},inverseJoinColumns = {@JoinColumn(name="ACTIVITY_UPDATE_ID")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private List<ActivityUpdate> listOfActivityUpdate = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="PATIENT_RECORD_MEDICINE", joinColumns = {@JoinColumn(name = "PATIENT_RECORDS_ID")}, inverseJoinColumns ={@JoinColumn(name="MED_UPDATE_ID")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private List<MedicineUpdate> listOfMedicineUpdate = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="PATIENT_RECORD_HABITS", joinColumns = {@JoinColumn(name = "PATIENT_RECORDS_ID")}, inverseJoinColumns ={@JoinColumn(name="HABIT_UPDATE_ID")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private List<HabitUpdate> listOfHabitUpdate = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="PATIENT_RECORD_MOOD_CONDITION", joinColumns = {@JoinColumn(name = "PATIENT_RECORDS_ID")}, inverseJoinColumns ={@JoinColumn(name="MOOD_CONDITION_ID")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private List<MoodConditionUpdate> listOfMoodCondition = new ArrayList<>();

    @Transient
    private SleepConditionAndDisorder sleepConditionAndDisorder;

    @OneToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="PATIENT_RECORD_SLEEP_CONDITION", joinColumns = {@JoinColumn(name = "PATIENT_RECORDS_ID")}, inverseJoinColumns ={@JoinColumn(name="SLEEP_CONDITION_ID")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private SleepConditionUpdate sleepCondition;

    @OneToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="PATIENT_RECORD_SLEEP_DISORDER", joinColumns = {@JoinColumn(name = "PATIENT_RECORDS_ID")}, inverseJoinColumns ={@JoinColumn(name="SLEEP_DISORDER_UPDATE_ID")})
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @CollectionId(columns = {@Column(name = "INDEX_ID")}, generator = "kaugen", type=@Type(type="long"))
    private List<SleepDisorderUpdate> sleepDisorderUpdate;

    @Override
    public String toString(){
        return String.format("{patientRecordID:%d,patientID:%s,patientLastUpdate:%s,listOfActivityUpdate:%s,listOfMedicineUpdate:%s,listOfHabitUpdate:%s,listOfMoodCondition:%s,sleepConditionAndDisorder:%s}",
                patientRecordID, patientID,patientLastUpdate, listOfActivityUpdate, listOfMedicineUpdate, listOfHabitUpdate, listOfMoodCondition, sleepConditionAndDisorder);
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

    public List<ActivityUpdate> getListOfActivityUpdate() {
        return listOfActivityUpdate;
    }

    public void setListOfActivityUpdate(List<ActivityUpdate> listOfActivityUpdate) {
        this.listOfActivityUpdate = listOfActivityUpdate;
    }

    public List<MedicineUpdate> getListOfMedicineUpdate() {
        return listOfMedicineUpdate;
    }

    public void setListOfMedicineUpdate(List<MedicineUpdate> listOfMedicine) {
        this.listOfMedicineUpdate = listOfMedicine;
    }

    public List<HabitUpdate> getListOfHabitUpdate() {
        return listOfHabitUpdate;
    }

    public void setListOfHabitUpdate(List<HabitUpdate> listOfHabitUpdate) {
        this.listOfHabitUpdate = listOfHabitUpdate;
    }

    public List<MoodConditionUpdate> getListOfMoodCondition() {
        return listOfMoodCondition;
    }

    public void setListOfMoodCondition(List<MoodConditionUpdate> listOfMoodCondition) {
        this.listOfMoodCondition = listOfMoodCondition;
    }

    public SleepConditionAndDisorder getSleepConditionAndDisorder() {
        return sleepConditionAndDisorder;
    }

    public void setSleepConditionAndDisorder(SleepConditionAndDisorder sleepConditionAndDisorder) {
        this.sleepConditionAndDisorder = sleepConditionAndDisorder;
    }

    public SleepConditionUpdate getSleepCondition() {
        return sleepCondition;
    }

    public void setSleepCondition(SleepConditionUpdate sleepCondition) {
        this.sleepCondition = sleepCondition;
    }

    public List<SleepDisorderUpdate> getSleepDisorderUpdate() {
        return sleepDisorderUpdate;
    }

    public void setListOfSleepDisorderUpdate(List<SleepDisorderUpdate> sleepDisorderUpdate) {
        this.sleepDisorderUpdate = sleepDisorderUpdate;
    }
}
