package com.dao;

import com.dm.Activity;
import com.dm.Medicine;
import com.dm.Patient;
import com.dm.PatientRecord;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.List;

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

//    public static void main(String args[]) {
////        PatientRecord p = new PatientRecord();
////        p.setPatient("2");
////       PatientRecordModel pm = new PatientRecordModel();
////       pm.addPatientActivitiesAndMedicinesByID(p);
////       String s = pm.getPatientRecordByID("110");
////       System.out.print(s)
//        session.createCriteria(MyEntity.class).list();
//    }
}
