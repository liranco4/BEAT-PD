package com.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static java.lang.String.format;

/**
 * Created by liran on 06/06/17.
 */
@Entity(name="HABIT")
public class Habit {
    @Id
    @Column(name = "HABIT_NAME")
    private String habitName;

    public Habit(){}
    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    @Override
    public String toString(){
        return format("{habitName:\"%s\"}",habitName);
    }
}