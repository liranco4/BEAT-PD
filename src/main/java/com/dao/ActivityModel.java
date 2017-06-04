package com.dao;

import com.dm.Activity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.String.format;

/**
 * Created by liran on 5/28/17.
 */
public class ActivityModel extends ModelGenerics {

    private ActivityModel(){}

    private static ActivityModel activityModelInstance;

    public static ActivityModel getActivityModelInstance(){
        if (activityModelInstance == null)
            activityModelInstance = new ActivityModel();
        return activityModelInstance;
    }

    public Collection<Activity> getAllActivityFromDB() throws HibernateException{
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            CriteriaQuery<Activity> cq = session.getCriteriaBuilder().createQuery(Activity.class);
            cq.from(Activity.class);
            List<Activity> activities = session.createQuery(cq).getResultList();
            transaction.commit();
            session.close();
            return activities;
    }

    public String getAllActivitiesAsJsonString(Collection<Activity> listOfActivity){
        try {
            return getObjectListAsJsonList(listOfActivity);
        }catch (HibernateException ex){
            return ex.getMessage();
        }
    }

    public Collection<Activity> getSelectedActivityByName(List<String> listOfActivityName){
        Collection<Activity> resultListOfPatientActivity = new ArrayList<>();
        Collection<Activity> listOfAllActivities = activityModelInstance.getAllActivityFromDB();
        for(String activityName : listOfActivityName){
            for(Activity activity: listOfAllActivities){
                if(activityName.equals(activity.getActivityName())){
                    resultListOfPatientActivity.add(activity);
                }
            }
        }
        return resultListOfPatientActivity;
    }
}
