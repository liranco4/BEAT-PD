package com.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

/**
 * Created by liran on 5/4/17.
 */
public class ModelGenerics{

    private static ModelGenerics modelGenericsInstance;
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static ModelGenerics getModelGenericsInstance(){
        if(modelGenericsInstance == null)
            modelGenericsInstance = new ModelGenerics();
        return modelGenericsInstance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

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

    public <T> Collection<T> findAllByClass(Class clazz) throws HibernateException{
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaQuery<T> cq = session.getCriteriaBuilder().createQuery(clazz);
        cq.from(clazz);
        List<T> objects = session.createQuery(cq).getResultList();
        transaction.commit();
        session.close();
        return objects;
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
        if(!objectList.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
