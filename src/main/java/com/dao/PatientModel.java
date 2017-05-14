package com.dao;

import com.dm.Activity;
import com.dm.Medicine;
import com.dm.Patient;
import com.fasterxml.jackson.core.JsonParser;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/**
 * Created by liran on 5/11/17.
 */
public class PatientModel extends ModelGenerics {
    public String addPatientToDB(Patient i_Patient){
        return addObjectToDB(i_Patient);
    }

    public String getPatientByID(String i_PatientName) {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, i_PatientName);
            patient.getListOfMedicine().add(new Medicine("DD","ss","dd","ff"));
            transaction.commit();
            session.close();
            return patient.toString();
        } catch (Exception e) {
            return String.format("{error:%s}", e.getMessage());
        }
    }

    public String updatePatientActivitiesAndMedicinesByID(String i_PatientName, Collection  <Activity> activities, Collection <Medicine> medicines) {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, i_PatientName);
            for (Medicine med : medicines){
                patient.getListOfMedicine().add(med);
            }
            for (Activity act : activities){
                patient.getListOfActivitiy().add(act);
            }
            transaction.commit();
            session.close();
            return patient.toString();
        } catch (Exception e) {
            return String.format("{error:%s}", e.getMessage());
        }
    }

}
