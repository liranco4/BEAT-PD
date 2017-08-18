package com.dm;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.utils.Utils.getObjectListAsJsonList;

/**
 * Created by liran on 5/4/17.
 */
@Entity(name = "ACTIVITIES")
public class Activity {
    @Id
    @Column(name = "ACTIVITY_NAME")
    private String activityName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "SUB_MENUS")
    private List<SubMenu> subMenus = new ArrayList<>();

    @Override
    public String toString(){
        return String.format("{activityName:\"%s\", subMenus:%s}", activityName, subMenus);
    }

    public Activity() {
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public List<SubMenu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<SubMenu> subMenus) {
        this.subMenus = subMenus;
    }
}
