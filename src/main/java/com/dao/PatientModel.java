package com.dao;


import com.dm.Activity;
import com.dm.Patient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

import static java.lang.String.format;

/**
 * Created by liran on 5/11/17.
 */
public class PatientModel extends ModelGenerics {

    private PatientModel(){}

    private static PatientModel patientModelInstance;

    public static PatientModel getPatientModelInstance(){
        if(patientModelInstance == null)
            patientModelInstance = new PatientModel();
        return patientModelInstance;
    }

    private ActivityModel activityModel = ActivityModel.getActivityModelInstance();

    public String getAllUpdatesByPatientID(String i_PatientID){

        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Patient patient = session.get(Patient.class, i_PatientID);
            List<String> listOfActivityName = session.createNativeQuery(format("select ACTIVITY_NAME from PATIENT_RECORD,PATIENT_RECORD_ACTIVITY where PATIENT_RECORD_ACTIVITY.PATIENT_RECORDS_ID=PATIENT_RECORD.PATIENT_RECORDS_ID and PATIENT_RECORD.PATIENT_ID=%s", i_PatientID)).list();

            transaction.commit();//TODO add list of all options
            session.close();

            Collection<Activity> patientActivities = activityModel.getSelectedActivityByName(listOfActivityName);
            StringBuilder buildPatientFile = new StringBuilder();
            buildPatientFile.append(format("***********************************************Start-Report-FOR-The-Following-Patient ID: %s ***********************************************\n",i_PatientID));
            buildPatientFile.append(format("%s\n",patient.toString()));
            buildPatientFile.append(format("%s\n",getObjectListAsJsonList(patientActivities)));
            buildPatientFile.append(format("***********************************************End-Report-FOR-The-Following-Patient ID: %s *************************************************\n",i_PatientID));
            return buildPatientFile.toString();
        } catch (HibernateException e) {
            return (format("{error:%s}", e.getMessage()));
        }
    }

    public static void main(String args[]){
        PatientModel patientModel = PatientModel.getPatientModelInstance();
        System.out.println(patientModel.getAllUpdatesByPatientID("1"));
    }
}
