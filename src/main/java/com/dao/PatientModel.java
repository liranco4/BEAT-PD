package com.dao;


import com.dm.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

import static java.lang.String.format;

/**
 * Created by liran on 5/11/17.
 */
public class PatientModel{
    private ModelGenerics modelGenerics = ModelGenerics.getModelGenericsInstance();

    private PatientModel(){}

    private static PatientModel patientModelInstance;

    public static PatientModel getPatientModelInstance(){
        if(patientModelInstance == null)
            patientModelInstance = new PatientModel();
        return patientModelInstance;
    }

    public List<PatientRecord> getAllPatientUpdates(){
        List<PatientRecord> patientRecords = new ArrayList<>();
        Collection<Patient> patients =  modelGenerics.findAllByClass(Patient.class);
        patients.forEach(p -> patientRecords.addAll(getAllUpdatesByPatientID(p.getPatientID())));
        return patientRecords;
    }

    public List<PatientRecord> getAllUpdatesByPatientID(String i_PatientID) throws HibernateException{
        Session session = null;
        try {
            session = modelGenerics.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<PatientRecord> patientRecords = session.createQuery(format("select p from PATIENT_RECORD as p where p.patientID=%s",i_PatientID)).list();
            transaction.commit();
            return patientRecords;
        } finally {
            if(session!=null)
                session.close();
        }
    }

    public Boolean checkCredentials(Patient patient){
        Patient patientDB = (Patient) modelGenerics.retrieveObjectFromDBbyID(Patient.class, patient.getPatientID());
        return patient.getPatientPass().equals(patientDB.getPatientPass());
    }

}
