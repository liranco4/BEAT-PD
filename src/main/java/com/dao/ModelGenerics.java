package com.dao;

import com.dm.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static java.lang.String.format;

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
     * @return json: on success, on failure: failure message
     */
    public String addObjectToDB(Object i_Object){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(i_Object);
            transaction.commit();
            session.close();
            return format("{success}");
        } catch(HibernateException e){
            return format("{error:%s}", e.getMessage());
        }
    }

    /**
     *
     * @param i_Object
     * @return json: on success, on failure: failure message
     */
    public String updateObjectToDB(Object i_Object){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(i_Object);
            transaction.commit();
            session.close();
            return format("{success}");
        } catch(HibernateException e){
            return format("{error:%s}", e.getMessage());
        }
    }

    /**
     *
     * @param objectList
     * @return return objectList as Json List
     */
    public <T> String getObjectListAsJsonList(Collection<T> objectList){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (Object object : objectList) {
            stringBuilder.append(object);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
