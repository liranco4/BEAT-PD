package com.dao;

import com.dm.*;
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
public class ActivityModel {

    private static ActivityModel activityModelInstance;
    private ModelGenerics modelGenerics = ModelGenerics.getModelGenericsInstance();

    private ActivityModel(){}

    public static ActivityModel getActivityModelInstance(){
        if (activityModelInstance == null)
            activityModelInstance = new ActivityModel();
        return activityModelInstance;
    }


    public Collection<Activity> getSelectedActivityByName(List<String> listOfActivityName){
        Collection<Activity> resultListOfPatientActivity = new ArrayList<>();
        Collection<Activity> listOfAllActivities = modelGenerics.findAllByClass(Activity.class);
        for(String activityName : listOfActivityName){
            for(Activity activity: listOfAllActivities){
                if(activityName.equals(activity.getActivityName())){
                    resultListOfPatientActivity.add(activity);
                }
            }
        }
        return resultListOfPatientActivity;
    }
public static void main(String args[]){
     ModelGenerics modelGenerics = ModelGenerics.getModelGenericsInstance();
    String s = format("{success: The following are all options,activities:%s,medicines:%s,habits:%s,links:%s,moodConditions:%s,sleepDisorders:%s}",
            modelGenerics.getObjectListAsJsonList(modelGenerics.findAllByClass(Activity.class)),
            modelGenerics.getObjectListAsJsonList(modelGenerics.findAllByClass(Medicine.class)),
            modelGenerics.getObjectListAsJsonList(modelGenerics.findAllByClass(Habit.class)),
            modelGenerics.getObjectListAsJsonList(modelGenerics.findAllByClass(Link.class)),
            modelGenerics.getObjectListAsJsonList(modelGenerics.findAllByClass(MoodCondition.class)),
            modelGenerics.getObjectListAsJsonList(modelGenerics.findAllByClass(SleepDisorder.class)));
    System.out.print(s);
}
}
