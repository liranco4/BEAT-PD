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
}
