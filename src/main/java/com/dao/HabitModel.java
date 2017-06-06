package com.dao;

import com.dm.Habit;
import org.hibernate.HibernateException;

import java.util.Collection;

/**
 * Created by liran on 06/06/17.
 */
public class HabitModel extends ModelGenerics {

    private HabitModel() {
    }

    private static HabitModel habitModelInstance;

    public static HabitModel getActivityModelInstance() {
        if (habitModelInstance == null)
            habitModelInstance = new HabitModel();
        return habitModelInstance;
    }

    public Collection<Habit> getAllHabitFromDB() throws HibernateException {
        return findAllByClass(Habit.class);
    }

    public String getAllHabitCollectionAsJsonString(Collection<Habit> habitCollection) {
        return getObjectListAsJsonList(habitCollection);
    }
}