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
        return findAllByClass(Activity.class);
    }

    public String getAllActivitiesAsJsonString(Collection<Activity> listOfActivity){
        return getObjectListAsJsonList(listOfActivity);
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
