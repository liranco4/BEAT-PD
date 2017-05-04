package com.dao;

import com.dm.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by liran on 5/4/17.
 */
public class ModelGenerics {
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    /**
     *
     * @param i_Object
     * @return json: on success, on failure: message failure
     */
    public String uploadObjectToDB(Object i_Object){
        User user;
        Activity activity;
        HospitalData hospitalData;
        Link link;
        Medicine medicine;
        Patient patient;

        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            try {
                if (i_Object instanceof User) {
                    user = (User) i_Object;
                    session.save(user);
                }
                if (i_Object instanceof Activity) {
                    activity = (Activity) i_Object;
                    session.save(activity);
                }
                if (i_Object instanceof HospitalData) {
                    hospitalData = (HospitalData) i_Object;
                    session.save(hospitalData);
                }
                if (i_Object instanceof Link) {
                    link = (Link) i_Object;
                    session.save(link);
                }
                if (i_Object instanceof Medicine) {
                    medicine = (Medicine) i_Object;
                    session.save(medicine);
                }
                if (i_Object instanceof Patient) {
                    patient = (Patient) i_Object;
                    session.save(patient);
                }
            } catch (ClassCastException e) {
                session.close();
                return String.format("{error:%s}", e.getMessage());
            }
            transaction.commit();
            session.close();
            return String.format("{success}");
        } catch(HibernateException e){
            return String.format("{error:%s}", e.getMessage());
        }
    }

}
