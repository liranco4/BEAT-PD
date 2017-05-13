package com.dao;

import com.dm.Activity;
import com.dm.Medicine;
import com.dm.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;



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
            transaction.commit();
            session.close();
            return patient.toString();
        } catch (Exception e) {
            return String.format("{error:%s}", e.getMessage());
        }
    }

    public String updatePatientToDB(Patient i_Patient){
        return updateObjectToDB(i_Patient);
    }

    public static void main(String args[]){
        PatientModel patientModelModel = new PatientModel();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Patient patient = mapper.readValue(patientModelModel.getPatientByID("223456789"), Patient.class);

        }catch(Exception e ){
            System.out.print(e.getMessage());
        }
        //patientModelModel.addPatientToDB(patient);
    }
}
