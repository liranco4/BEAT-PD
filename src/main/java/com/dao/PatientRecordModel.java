package com.dao;

import com.dm.ActivityUpdate;
import com.dm.HabitUpdate;
import com.dm.Patient;
import com.dm.PatientRecord;
import org.hibernate.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public String addPatientRecord(PatientRecord i_PatientRecord)throws HibernateException{
        Session session = null;
        try {
            session = modelGenerics.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, i_PatientRecord.getPatientID());
            if (patient == null)
                throw new IllegalArgumentException(format("The following patientID:%s doesn't exist", i_PatientRecord.getPatientID()));

            //Save sleep condition object on SLEEP_CONDITION table
            addSleepConditionIfExist(i_PatientRecord,session).ifPresent((id)->i_PatientRecord.setSleepConditionID(id));
            //Save activity update list on ACTIVITY_UPDATE table
            addActivityUpdateIfExist(i_PatientRecord,session).ifPresent((idList)->i_PatientRecord.setListOfActivityUpdate(idList));
            //Save habit update list on HABIT_UPDATE table
            addHabitUpdateIfExist(i_PatientRecord,session).ifPresent((idList)->i_PatientRecord.setListOfHabitUpdate(idList));
            session.save(i_PatientRecord);
            transaction.commit();
            session.close();
            return i_PatientRecord.toString();
        } finally {
            if(session!=null)
                session.close();
        }
    }

    private Optional<Long> addSleepConditionIfExist(PatientRecord i_PatientRecord, Session i_Session){
        if (i_PatientRecord.getSleepCondition() != null) {
            i_Session.save(i_PatientRecord.getSleepCondition());
            return Optional.of(i_PatientRecord.getSleepCondition().getSleepConditionID());
        }
        return Optional.empty();
    }

    private Optional<List<ActivityUpdate>> addActivityUpdateIfExist(PatientRecord i_PatientRecord, Session i_Session){
        if (i_PatientRecord.getListOfActivityUpdate() != null) {
            List<ActivityUpdate> resultList = new ArrayList<>();
            for (ActivityUpdate activity: i_PatientRecord.getListOfActivityUpdate()) {
                i_Session.save(activity);
                resultList.add(activity);
            }
            return Optional.of(resultList);
        }
        return Optional.empty();
    }

    private Optional<List<HabitUpdate>> addHabitUpdateIfExist(PatientRecord i_PatientRecord, Session i_Session){
        if (i_PatientRecord.getListOfHabitUpdate() != null) {
            List<HabitUpdate> resultList = new ArrayList<>();
            for (HabitUpdate habitUpdate: i_PatientRecord.getListOfHabitUpdate()) {
                i_Session.save(habitUpdate);
                resultList.add(habitUpdate);
            }
            return Optional.of(resultList);
        }
        return Optional.empty();
    }
}
