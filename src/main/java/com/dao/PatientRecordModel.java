package com.dao;

import com.dm.Patient;
import com.dm.PatientRecord;
import org.hibernate.*;

import static java.lang.String.format;

/**
 * Created by liran on 5/11/17.
 */
public class PatientRecordModel {

    private ModelGenerics modelGenerics = ModelGenerics.getModelGenericsInstance();
    private static PatientRecordModel patientRecordModelInstance;

    private PatientRecordModel(){}

    public static PatientRecordModel getPatientRecordModelInstance(){
        if(patientRecordModelInstance == null)
            patientRecordModelInstance = new PatientRecordModel();
        return patientRecordModelInstance;
    }

    public String addPatientRecord(PatientRecord i_PatientRecord) {
        try {
            Session session = modelGenerics.getSessionFactory().openSession();
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

}
