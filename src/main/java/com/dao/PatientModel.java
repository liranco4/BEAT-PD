package com.dao;

import com.dm.Activity;
import com.dm.Medicine;
import com.dm.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by liran on 5/11/17.
 */
public class PatientModel extends ModelGenerics {
    public String addPatientToDB(Patient i_Patient){
        return addObjectToDB(i_Patient);
    }

    public String getPatientByName(String i_PatientName) {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, i_PatientName);
            transaction.commit();
            session.close();
            return patient.toString();
        } catch (Exception e) {
            return String.format("{error:%s}", e.getMessage());
        }
    }
    public static void main(String args[]){
        PatientModel patientModelModel = new PatientModel();
        Patient patient = new Patient("dd", "ss","dd", "ok", "80");
        Activity activity1 = new Activity("sport1","sport","fun");
        Activity activity2 = new Activity("sport2","sport","fun");
        Medicine medicine1 = new Medicine("123", "advill","none","bla bla");
        Medicine medicine2 = new Medicine("124", "akamol","none","bla bla");
        patient.getListOfActivitiy().add(activity1);
        patient.getListOfActivitiy().add(activity2);
        patient.getListOfMedicine().add(medicine1);
        patient.getListOfMedicine().add(medicine2);
        patientModelModel.addPatientToDB(patient);
    }
}
