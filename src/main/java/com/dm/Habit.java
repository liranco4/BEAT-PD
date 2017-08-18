package com.dm;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * Created by liran on 06/06/17.
 */
@Entity(name="HABIT")
public class Habit {
    @Id
    @Column(name = "HABIT_NAME")
    private String habitName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "SUB_MENUS")
    private List<SubMenu> subMenus = new ArrayList<>();

    public Habit(){}
    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public List<SubMenu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<SubMenu> subMenus) {
        this.subMenus = subMenus;
    }

    @Override
    public String toString(){
        return format("{habitName:\"%s\", subMenus:%s}",habitName, subMenus);
    }
}