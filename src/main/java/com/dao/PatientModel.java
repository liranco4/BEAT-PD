package com.dao;


import com.dm.*;
import com.dm.dbResult.ActivityDBResult;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.utils.Utils.getObjectListAsJsonList;
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

    public List<PatientRecord> getAllUpdatesByPatientID(String i_PatientID)throws HibernateException{
        Session session = null;
        try {
            session = modelGenerics.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<PatientRecord> patientRecords = session.createQuery(format("select distinct p from PATIENT_RECORD as p join p.listOfActivityUpdate with p.patientID=%s",i_PatientID)).list();
            transaction.commit();
            return patientRecords;
        } finally {
            if(session!=null)
                session.close();
        }
    }
//    private String buildReport(List<PatientRecord> i_PatientRecords){
//        StringBuilder buildPatientFile = new StringBuilder();
//        buildPatientFile.append(format("***********************************************Start-Report-FOR-The-Following-Patient ID: %s ***********************************************\n",i_PatientID));
//        buildPatientFile.append(format("%s\n",i_PatientRecords.toString()));
//        buildPatientFile.append(format("%s\n",getObjectListAsJsonList(i_PatientRecords)));
//        buildPatientFile.append(format("***********************************************End-Report-FOR-The-Following-Patient ID: %s *************************************************\n",i_PatientID));
//        return buildPatientFile.toString();
//    }

    public static void main(String args[]){
        PatientModel patientModel = new PatientModel();
//        patientModel.getAllUpdatesByPatientID("1");
//        Link link = new Link();
//        link.setLinkHeadLine("news");
//        link.setLinkURL("url of news");
//        Link link2 = new Link();
//        link2.setLinkHeadLine("news2");
//        link2.setLinkURL("url of news2");
//        ModelGenerics.getModelGenericsInstance().addObjectToDB(link);
//        ModelGenerics.getModelGenericsInstance().addObjectToDB(link2);
        //patientModel.getAllUpdatesByPatientID("1")
        List<PatientRecord> list =patientModel.getAllUpdatesByPatientID("1");
        for(PatientRecord l:list){
            for(ActivityUpdate w:l.getListOfActivityUpdate()){
                System.out.println(w.getActivityName());
            }
        }

    }
}
