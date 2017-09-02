package com.dao;

import com.dm.SleepConditionAndDisorder;
import org.hibernate.HibernateException;

import java.util.Collection;

import static com.utils.Utils.getObjectListAsJsonList;

/**
 * Created by liran on 07/06/17.
 */
public class SleepConditionModel extends ModelGenerics{
    private SleepConditionModel() {
    }

    private static SleepConditionModel sleepConditionModel;

    public static SleepConditionModel getSleepConditionModelInstance() {
        if (sleepConditionModel == null)
            sleepConditionModel = new SleepConditionModel();
        return sleepConditionModel;
    }

    public Collection<SleepConditionAndDisorder> getAllSleepConditionFromDB() throws HibernateException {
        return findAllByClass(SleepConditionAndDisorder.class);
    }

    public String getAllSleepConditionsAsJsonString(Collection<SleepConditionAndDisorder> sleepConditionAndDisorders) {
        return getObjectListAsJsonList(sleepConditionAndDisorders);
    }
}
