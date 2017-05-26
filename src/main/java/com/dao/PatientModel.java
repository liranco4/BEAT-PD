package com.dao;

import com.dm.Activity;
import com.dm.Medicine;
import com.dm.Patient;
import com.fasterxml.jackson.core.JsonParser;
import com.utils.CustomDate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by liran on 5/11/17.
 */
public class PatientModel extends ModelGenerics {
    public String addPatientToDB(Patient i_Patient) {
        return addObjectToDB(i_Patient);
    }

    public String getPatientByID(String i_PatientName) {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, i_PatientName);
            patient.getListOfMedicine().add(new Medicine("DD", "ss", "dd", "ff"));
            transaction.commit();
            session.close();
            return patient.toString();
        } catch (Exception e) {
            return String.format("{error:%s}", e.getMessage());
        }
    }

    public String updatePatientActivitiesAndMedicinesByID(Patient i_Patient) {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, i_Patient.getPatientID());
            for (Medicine med : patient.getListOfMedicine()) {
                patient.getListOfMedicine().add(med);
            }
            for (Activity act : patient.getListOfActivitiy()) {
                patient.getListOfActivitiy().add(act);
            }
            patient.setPatientLastUpdate(i_Patient.getPatientLastUpdate());
            transaction.commit();
            session.close();
            return patient.toString();
        } catch (Exception e) {
            return String.format("{error:%s}", e.getMessage());
        }
    }

    public static void main(String args[]) {Patient p = new Patient("110", "sds", "sdf", "dsf", "df");
       PatientModel pm = new PatientModel();
       pm.addPatientToDB(p);
//       String s = pm.getPatientByID("110");
//       System.out.print(s)
    }
}
