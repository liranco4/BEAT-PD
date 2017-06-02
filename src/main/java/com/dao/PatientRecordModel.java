package com.dao;

import com.dm.Activity;
import com.dm.Medicine;
import com.dm.Patient;
import com.dm.PatientRecord;
import org.hibernate.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liran on 5/11/17.
 */
public class PatientRecordModel extends ModelGenerics {
    public String addPatientToDB(PatientRecord i_PatientRecord) {
        return addObjectToDB(i_PatientRecord);
    }

    public String addPatientActivitiesAndMedicinesByID(PatientRecord i_PatientRecord) {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, i_PatientRecord.getPatientID());
            if(patient == null)
                throw new IllegalArgumentException(String.format("The following patientID:%s doesn't exist",i_PatientRecord.getPatientID()));
            session.save(i_PatientRecord);
            transaction.commit();
            session.close();
            return i_PatientRecord.toString();
        } catch (HibernateException e) {
            return String.format("{error:%s}", e.getMessage());
        }
    }

    public static void main(String args[]) {
        PatientRecordModel patientRecordModel = new PatientRecordModel();
        PatientRecord i_PatientRecord = new PatientRecord();
//        try {
//            Session session = patientRecordModel.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//            List<Activity> activities = session.createQuery("SELECT activityName FROM PATIENT_RECORD WHERE patientID=1", Activity.class).list();
//            session.save(i_PatientRecord);
//            transaction.commit();
//            session.close();
//            System.out.print(i_PatientRecord.toString());
//        } catch (HibernateException e) {
//            System.out.print(String.format("{error:%s}", e.getMessage()));
//        }

        i_PatientRecord.getListOfActivitiy().add(new Activity("ss","ss","ss"));
        i_PatientRecord.getListOfActivitiy().add(new Activity("s1","ss","ss"));
        i_PatientRecord.setPatient("2");
        i_PatientRecord.setPatientLastUpdate(new Date());
        patientRecordModel.addPatientActivitiesAndMedicinesByID(i_PatientRecord);
    }
}
