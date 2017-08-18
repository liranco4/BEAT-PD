package com.dao;




import com.dm.Activity;
import com.dm.KeepAlive;
import com.dm.SleepQuality;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.utils.SingleLogger.LOGGER;
import static java.lang.String.format;

/**
 * Created by liran on 5/4/17.
 */
public class ModelGenerics {

    private static ModelGenerics modelGenericsInstance;
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static boolean keepUpFlag = false;

    public static ModelGenerics getModelGenericsInstance() {
        if (modelGenericsInstance == null)
            modelGenericsInstance = new ModelGenerics();
        return modelGenericsInstance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param i_Object
     * @return json: on success, on failure: failure message
     */
    public String addObjectToDB(Object i_Object) throws HibernateException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(i_Object);
            transaction.commit();
            LOGGER.log(Level.INFO, "add object to DB succeeded");
            return format("{success}");
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * @param i_Object
     * @return json: on success, on failure: failure message
     */
    public String updateObjectToDB(Object i_Object) throws HibernateException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(i_Object);
            transaction.commit();
            LOGGER.log(Level.INFO, "update object to DB succeeded");
            return format("{success}");
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * @param clazz
     * @param id
     * @return object: on success, on failure: failure message
     */
    public Object retrieveObjectFromDBbyID(Class clazz, String id) throws HibernateException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Object object = session.get(clazz, id);
            transaction.commit();
            if (object == null) {
                LOGGER.log(Level.INFO, format("error retrieve object: %s from DB by ID: %s failed", clazz.getName(), id));
                throw new NoResultException(format("error retrieve object: %s from DB by ID: %s failed", clazz.getName(), id));
            } else
                LOGGER.log(Level.INFO, format("retrieve object: %s from DB by ID: %s succeeded", clazz.getName(), id));
            return object;
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * @param clazz
     * @param id
     * @return object: on success, on failure: failure message
     */
    public Object retrieveObjectFromDBbyID(Class clazz, Long id) throws HibernateException, NoResultException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Object object = session.load(clazz, id);
            transaction.commit();
            if (object == null) {
                LOGGER.log(Level.INFO, format("error retrieve object: %s from DB by ID: %s failed", clazz.getName(), id));
                throw new NoResultException(format("error retrieve object: %s from DB by ID: %s failed", clazz.getName(), id));
            } else
                LOGGER.log(Level.INFO, format("retrieve object: %s from DB by ID: %d succeeded", clazz.getName(), id));
            return object;
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * @param clazz
     * @param <T>
     * @return all by class name
     * @throws HibernateException
     */
    public <T> Collection<T> findAllByClass(Class clazz) throws HibernateException {
        Collection<T> objects;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Hibernate.initialize(Activity.class);
            CriteriaQuery<T> cq = session.getCriteriaBuilder().createQuery(clazz);
            cq.from(clazz);
            objects = session.createQuery(cq).getResultList();
            transaction.commit();
            LOGGER.log(Level.INFO, format("find all by class: %s succeeded", clazz.getName()));
            if (!keepUpFlag) {
                LOGGER.log(Level.INFO, "keep alive is on");
                keepConnectionToDBUp(1);
                keepUpFlag = true;
            }
            return objects;
        } finally {
            if (session != null)
                session.close();
        }
    }


    private void keepConnectionToDBUp(long keepAliveID) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            while (true) {
                try {
                    TimeUnit.MINUTES.sleep(1);
                    retrieveObjectFromDBbyID(KeepAlive.class, keepAliveID);
                    LOGGER.log(Level.INFO, "keep alive invoke the DB");
                    TimeUnit.HOURS.sleep(5);
                } catch (NoResultException e) {
                    LOGGER.log(Level.INFO, format("error keep alive can't find ID: %d for the following table: %s", keepAliveID, KeepAlive.class.getName()));
                } catch (HibernateException e) {
                    LOGGER.log(Level.INFO, "error keep alive in hibernate connection");
                }
            }
        });
    }
}