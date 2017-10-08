package com.dm.updateDM;

import com.interfaces.UpdateDMProxy;

import javax.persistence.*;

import static java.lang.String.format;

/**
 * Created by liran on 12/08/17.
 */
@Entity(name="HABIT_UPDATE")
public class HabitUpdate implements UpdateDMProxy {
    @Id
    @SequenceGenerator(name = "HABIT_UPDATE_SEQ", sequenceName = "HABIT_UPDATE_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "HABIT_UPDATE_SEQ")
    @Column(name = "HABIT_UPDATE_ID")
    private Long habitUpdateID;

    @Column(name = "HABIT_NAME")
    private String habitName;

    @Column(name="HABIT_DESCRIPTION")
    private String habitDescription;

    public HabitUpdate(){}

    @Override
    public String toString(){
        return format("habitName:\"%s\",habitDescription:\"%s\"",habitName,habitDescription);
    }

    public Long getHabitUpdateID() {
        return habitUpdateID;
    }

    public void setHabitUpdateID(Long habitUpdateID) {
        this.habitUpdateID = habitUpdateID;
    }

    @Override
    public String getFirstDetail() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    @Override
    public String getSecondDetail() {
        return habitDescription;
    }

    public void setHabitDescription(String habitDescription) {
        this.habitDescription = habitDescription;
    }
}
