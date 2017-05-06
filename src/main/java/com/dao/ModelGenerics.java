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
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(i_Object);
            transaction.commit();
            session.close();
            return String.format("{success}");
        } catch(HibernateException e){
            return String.format("{error:%s}", e.getMessage());
        }
    }

}
