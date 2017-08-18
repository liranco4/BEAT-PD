package com.dm;

import org.hibernate.annotations.Proxy;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import static java.lang.String.format;

/**
 * Created by liran on 17/08/17.
 */
@Embeddable
@Proxy(lazy = false)
public class SubMenu {
    private String subMenu;
    public SubMenu(){}
    @Override
    public String toString(){
        return format("subMenu:\"%s\"",subMenu);
    }

    public String getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(String subMenu) {
        this.subMenu = subMenu;
    }
}
