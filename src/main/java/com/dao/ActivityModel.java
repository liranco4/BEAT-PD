package com.dao;

import com.dm.Activity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by liran on 5/28/17.
 */
public class ActivityModel extends ModelGenerics {

    public String getAllActivityFromDB() {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            CriteriaQuery<Activity> cq = session.getCriteriaBuilder().createQuery(Activity.class);
            cq.from(Activity.class);
            List<Activity> activities = session.createQuery(cq).getResultList();
            transaction.commit();
            session.close();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            for (Object activity : activities) {
                stringBuilder.append(activity);
                stringBuilder.append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append("]");
            return stringBuilder.toString();
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }
}
