package com.dao;

import com.dm.Activity;
import com.dm.Patient;
import com.dm.PatientRecord;
import org.hibernate.*;

import java.util.*;

import static java.lang.String.format;

/**
 * Created by liran on 5/11/17.
 */
public class PatientRecordModel extends ModelGenerics {

    private PatientRecordModel(){}

    private static PatientRecordModel patientRecordModelInstance;

    public static PatientRecordModel getPatientRecordModelInstance(){
        if(patientRecordModelInstance == null)
            patientRecordModelInstance = new PatientRecordModel();
        return patientRecordModelInstance;
    }

    public String addPatientToDB(PatientRecord i_PatientRecord) {
        return addObjectToDB(i_PatientRecord);
    }

    public String addPatientRecord(PatientRecord i_PatientRecord) {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, i_PatientRecord.getPatientID());
            if(patient == null)
                throw new IllegalArgumentException(format("The following patientID:%s doesn't exist", i_PatientRecord.getPatientID()));
            session.save(i_PatientRecord);
            transaction.commit();
            session.close();
            return i_PatientRecord.toString();
        } catch (HibernateException e) {
            return format("{error:%s}", e.getMessage());
        }
    }


//    public static void main(String args[]) {
//        PatientRecordModel patientRecordModel = new PatientRecordModel();
//        ActivityModel activityModel = ActivityModel.getActivityModelInstance();
//        PatientRecord i_PatientRecord = new PatientRecord();
//        try {
//            Session session = patientRecordModel.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//            //List<PatientRecord> patients = session.createQuery("SELECT patientRecordID FROM PATIENT_RECORD patientRecordID", PatientRecord.class).list();
//            List<String> listOfActivityName = session.createNativeQuery("select ACTIVITY_NAME from PATIENT_RECORD,PATIENT_RECORD_ACTIVITY where PATIENT_RECORD_ACTIVITY.PATIENT_RECORDS_ID=PATIENT_RECORD.PATIENT_RECORDS_ID and PATIENT_RECORD.PATIENT_ID=1").list();
//
////            session.save(i_PatientRecord);
//            transaction.commit();
//            session.close();
//            Collection<Activity> activities = activityModel.getAllActivityFromDB();
//            for(String activityName : listOfActivityName){
//                for(Activity activity: activities){
//                    if(activityName.equals(activity.getActivityName())){
//                        //add activity patient_record where patientId=1
//                    }
//                }
//            }
//            System.out.print(i_PatientRecord.toString());
//        } catch (HibernateException e) {
//            System.out.print(format("{error:%s}", e.getMessage()));
//        }
//
//    }
}
